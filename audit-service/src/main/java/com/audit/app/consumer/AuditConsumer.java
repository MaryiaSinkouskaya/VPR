package com.audit.app.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AuditConsumer {

  @KafkaListener(topics = "audit-log", groupId = "audit-service")
  public void listen(AuditEvent event) {
    // Save event to DB
    System.out.println("AUDIT: " + event);
  }
}
