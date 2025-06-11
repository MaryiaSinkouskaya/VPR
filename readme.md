## Application for distributed access to the updated Republican database of congenital malformations

### VPR Platform overview

This is a Spring Boot-based platform that includes:

- A core application (`vpr-service`) for managing domain logic and REST APIs. Implemented basic CRUD operations. JWT
  authentication applied. Key roles: ADMIN, DOCTOR, VIEWER. Only admin can manage other users and create user with any
  role. After registration the only role assigned is VIEWER.
- A Kafka-backed audit microservice (`audit-service`) for recording system events like logins, updates, and
  registrations

---

### Tech Stack

- Java 17
- Spring Boot 3
- Apache Kafka (Bitnami, KRaft mode)
- PostgreSQL 13.3 (app) + PostgreSQL 15 (audit)
- Docker Compose
- JWT-based authentication
- Multi-module Maven structure

---

### Architecture overview

- **Kafka** is used as an event bus for audit logs.
- **vpr-service** produces `AuditEvent` messages to the `audit-log` topic.
- **audit-service** consumes those events and stores them in a dedicated audit database.

### Modules

#### shared

- Contains common classes shared between microservices, such as:
    - AuditEvent – the Kafka message payload used for audit logging
- Packaged as a standalone Maven module and included as a dependency in both vpr-service and audit-service

#### vpr-service

- Main Spring Boot application
- Exposes REST APIs for core business operations
- Performs authentication and authorization using JWT
- Publishes AuditEvent messages to Kafka upon key actions (e.g. login, user registration, data changes)
- Persists domain data to PostgreSQL (db)

#### audit-service

- Kafka consumer microservice
- Subscribes to the audit-log Kafka topic
- Consumes AuditEvent messages and stores them in a dedicated PostgreSQL database (audit-db)
- Can be extended to expose a REST API for viewing logs or querying audit data

---

### Prerequisites:

- Maven 3
- JDK 17
- Docker
- add .env file likewise in env_example.txt

### How to build:

    mvn clean install

### Start application

    docker compose up      

### Stop & remove images

    docker compose down --volumes && docker rmi vpr-service audit-service

---

### Run Stress test

`mvn test -Dload.test=true`

---

### DB schema sructure

- PROBAND: proband description;
- ABNORMALITY: list of abnormalities;
- MOTHER: unique information about the mother, such as age at diagnosis or date of last menstruation;
- PERSON_INFO: stores unified personal information (mother, father of proband);
- ADDRESS: the address of the organization or the residence of the family of proband;
- WORKPLACE: information about parents workplace;
- PROB_D: information if proband is dead;
- ORGANIZATION: information about clinic;
- DOCTOR: information about healthcare provider;
- NOTE: additional info.

![schema](schema.png)

### OpenApi doc

`${vpr.openapi.dev-url}/swagger-ui/index.html#/`
