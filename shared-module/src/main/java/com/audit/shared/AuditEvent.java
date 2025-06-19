package com.audit.shared;

import java.time.Instant;

/**
 * Audit event for tracking entity changes.
 *
 * @param eventType    The type of event (CREATE, UPDATE, DELETE, etc.)
 * @param entityType   The type of entity (e.g., "User")
 * @param entityId     The ID of the entity
 * @param performedBy  The user role and user ID who performed the action (e.g., "ADMIN:42")
 * @param timestamp    The time the event occurred
 * @param metadata     Any additional metadata
 */
public record AuditEvent(
    String eventType,
    String entityType,
    String entityId,
    String performedBy,
    Instant timestamp,
    String metadata
) {
}