package com.audit.app.consumer;

import java.time.Instant;

public record AuditEvent(
    String eventType,
    String entityName,
    String entityId,
    String performedBy,
    Instant timestamp,
    String metadata
) {}