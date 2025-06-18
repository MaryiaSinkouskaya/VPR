package com.vpr.app.audit.log;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import com.audit.shared.AuditEvent;
import com.vpr.app.audit.log.annotation.AuditCreate;
import com.vpr.app.audit.log.annotation.AuditDelete;
import com.vpr.app.audit.log.annotation.AuditUpdate;
import com.vpr.app.audit.log.kafka.KafkaAuditProducer;
import com.vpr.app.security.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Instant;

/**
 * Aspect for handling audit logging through Kafka.
 * Intercepts methods annotated with @AuditCreate, @AuditUpdate, and @AuditDelete
 * to produce audit events.
 */
@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AuditAspect {

    private static final String ACTION_CREATE = "CREATE";
    private static final String ACTION_UPDATE = "UPDATE";
    private static final String ACTION_DELETE = "DELETE";
    private static final String LOG_AUDIT_SUCCESS = "Sent audit event: action={}, entity={}, id={}, performedBy={}";
    private static final String LOG_AUDIT_ERROR = "Failed to process audit for action {} on entity {}: {}";
    public static final String UNKNOWN = "UNKNOWN";

    private final KafkaAuditProducer auditProducer;

    /**
     * Handles audit events for create operations.
     */
    @AfterReturning(pointcut = "@annotation(auditCreate)", returning = "result")
    public void afterCreate(JoinPoint joinPoint, Object result, AuditCreate auditCreate) {
        handleAudit(joinPoint, result, ACTION_CREATE, auditCreate.entity());
    }

    /**
     * Handles audit events for update operations.
     */
    @AfterReturning(pointcut = "@annotation(auditUpdate)", returning = "result")
    public void afterUpdate(JoinPoint joinPoint, Object result, AuditUpdate auditUpdate) {
        handleAudit(joinPoint, result, ACTION_UPDATE, auditUpdate.entity());
    }

    /**
     * Handles audit events for delete operations.
     */
    @AfterReturning(pointcut = "@annotation(auditDelete)", returning = "result")
    public void afterDelete(JoinPoint joinPoint, Object result, AuditDelete auditDelete) {
        handleAudit(joinPoint, result, ACTION_DELETE, auditDelete.entity());
    }

    /**
     * Core method for handling audit events.
     */
    private void handleAudit(JoinPoint joinPoint, Object result, String action, String entity) {
        try {
            String id = extractId(result, joinPoint.getArgs());
            String performedBy = getCurrentUserRoleAndId();
            AuditEvent event = new AuditEvent(
                action,
                entity,
                id,
                performedBy,
                Instant.now(),
                null
            );
            auditProducer.send(event);
            log.debug(LOG_AUDIT_SUCCESS, action, entity, id, performedBy);
        } catch (Exception e) {
            log.error(LOG_AUDIT_ERROR, action, entity, e.getMessage(), e);
        }
    }

    private String extractId(Object result, Object[] args) {
        String id = extractIdFromObject(result);
        if (!id.isEmpty()) {
            return id;
        }
        for (Object arg : args) {
            id = extractIdFromObject(arg);
            if (!id.isEmpty()) {
                return id;
            }
        }
        return EMPTY;
    }

    private String extractIdFromObject(Object obj) {
        if (obj == null) return EMPTY;
        try {
            Method getIdMethod = obj.getClass().getMethod("getId");
            Object idValue = getIdMethod.invoke(obj);
            return idValue != null ? idValue.toString() : EMPTY;
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
            return EMPTY;
        }
    }

    private String getCurrentUserRoleAndId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User user) {
            String role = user.getRole() != null ? user.getRole().name() : UNKNOWN;
            Long userId = user.getId();
            return role + ':' + (userId != null ? userId : UNKNOWN);
        }
        return UNKNOWN + ':' + UNKNOWN;
    }
}