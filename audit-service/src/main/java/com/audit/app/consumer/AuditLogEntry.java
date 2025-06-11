package com.audit.app.consumer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.Instant;

@Entity
public class AuditLogEntry {
  @Id
  @GeneratedValue
  private Long id;
  private String eventType;
  private String entityName;
  private String entityId;
  private String performedBy;
  private Instant timestamp;
  private String metadata;
}
