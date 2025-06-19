package com.audit.app.consumer;

import com.audit.shared.AuditEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuditConsumer {

    @KafkaListener(
            topics = "audit-User",
            groupId = "audit-service",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenUserEvents(
            AuditEvent event,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition
    ) {
        log.info("USER AUDIT: {} from topic: {} partition: {}", event, topic, partition);
    }
}
