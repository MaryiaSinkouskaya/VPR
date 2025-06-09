package com.vpr.app.audit.log.kafka;

import java.time.Instant;

public record AuditEvent(
    String eventType,
    String entityName,
    String entityId,
    String performedBy,
    Instant timestamp,
    String metadata
) {}