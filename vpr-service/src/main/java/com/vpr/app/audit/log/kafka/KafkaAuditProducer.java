package com.vpr.app.audit.log.kafka;

import com.audit.shared.AuditEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaAuditProducer {
  private final KafkaTemplate<String, AuditEvent> kafkaTemplate;

  public void send(AuditEvent event) {
    String topic = "audit-" + event.entityType();
    kafkaTemplate.send(topic, event);
  }
}
