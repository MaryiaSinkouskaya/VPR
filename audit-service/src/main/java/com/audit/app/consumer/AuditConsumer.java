package com.audit.app.consumer;

import com.audit.shared.AuditEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuditConsumer {

  @KafkaListener(topicPattern = "audit-.*", groupId = "audit-service")
  public void listen(AuditEvent event) {
    // Save event to DB
    log.info("AUDIT: " + event);//todo fix delay
  }
}
