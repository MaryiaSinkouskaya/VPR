package com.audit.app.consumer;

import com.audit.shared.AuditEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import static org.springframework.kafka.support.KafkaHeaders.RECEIVED_PARTITION;
import static org.springframework.kafka.support.KafkaHeaders.RECEIVED_TOPIC;

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
            @Header(RECEIVED_TOPIC) String topic,
            @Header(RECEIVED_PARTITION) int partition
    ) {
        log.info("USER AUDIT: {} from topic: {} partition: {}", event, topic, partition);
    }

    @KafkaListener(
            topics = "audit-Abnormality",
            groupId = "audit-service",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenAbnormalityEvents(
            AuditEvent event,
            @Header(RECEIVED_TOPIC) String topic,
            @Header(RECEIVED_PARTITION) int partition
    ) {
        log.info("ABNORMALITY AUDIT: {} from topic: {} partition: {}", event, topic, partition);
    }

    @KafkaListener(
            topics = "audit-Address",
            groupId = "audit-service",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenAddressEvents(
            AuditEvent event,
            @Header(RECEIVED_TOPIC) String topic,
            @Header(RECEIVED_PARTITION) int partition
    ) {
        log.info("ADDRESS AUDIT: {} from topic: {} partition: {}", event, topic, partition);
    }

    @KafkaListener(
            topics = "audit-Doctor",
            groupId = "audit-service",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenDoctorEvents(
            AuditEvent event,
            @Header(RECEIVED_TOPIC) String topic,
            @Header(RECEIVED_PARTITION) int partition
    ) {
        log.info("DOCTOR AUDIT: {} from topic: {} partition: {}", event, topic, partition);
    }

    @KafkaListener(
            topics = "audit-Mother",
            groupId = "audit-service",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenMotherEvents(
            AuditEvent event,
            @Header(RECEIVED_TOPIC) String topic,
            @Header(RECEIVED_PARTITION) int partition
    ) {
        log.info("MOTHER AUDIT: {} from topic: {} partition: {}", event, topic, partition);
    }

    @KafkaListener(
            topics = "audit-Note",
            groupId = "audit-service",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenNoteEvents(
            AuditEvent event,
            @Header(RECEIVED_TOPIC) String topic,
            @Header(RECEIVED_PARTITION) int partition
    ) {
        log.info("NOTE AUDIT: {} from topic: {} partition: {}", event, topic, partition);
    }

    @KafkaListener(
            topics = "audit-Organization",
            groupId = "audit-service",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenOrganizationEvents(
            AuditEvent event,
            @Header(RECEIVED_TOPIC) String topic,
            @Header(RECEIVED_PARTITION) int partition
    ) {
        log.info("ORGANIZATION AUDIT: {} from topic: {} partition: {}", event, topic, partition);
    }

    @KafkaListener(
            topics = "audit-PersonInfo",
            groupId = "audit-service",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenPersonInfoEvents(
            AuditEvent event,
            @Header(RECEIVED_TOPIC) String topic,
            @Header(RECEIVED_PARTITION) int partition
    ) {
        log.info("PERSON INFO AUDIT: {} from topic: {} partition: {}", event, topic, partition);
    }

    @KafkaListener(
            topics = "audit-Proband",
            groupId = "audit-service",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenProbandEvents(
            AuditEvent event,
            @Header(RECEIVED_TOPIC) String topic,
            @Header(RECEIVED_PARTITION) int partition
    ) {
        log.info("PROBAND AUDIT: {} from topic: {} partition: {}", event, topic, partition);
    }

    @KafkaListener(
            topics = "audit-ProbandD",
            groupId = "audit-service",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenProbandDEvents(
            AuditEvent event,
            @Header(RECEIVED_TOPIC) String topic,
            @Header(RECEIVED_PARTITION) int partition
    ) {
        log.info("PROBAND D AUDIT: {} from topic: {} partition: {}", event, topic, partition);
    }

    @KafkaListener(
            topics = "audit-Workplace",
            groupId = "audit-service",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenWorkplaceEvents(
            AuditEvent event,
            @Header(RECEIVED_TOPIC) String topic,
            @Header(RECEIVED_PARTITION) int partition
    ) {
        log.info("WORKPLACE AUDIT: {} from topic: {} partition: {}", event, topic, partition);
    }
}