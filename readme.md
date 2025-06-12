# VPR Platform: Distributed Access to the Republican Database of Congenital Malformations

## Table of Contents

1. [Overview](#overview)
2. [Key Features](#key-features)
3. [Tech Stack](#tech-stack)
4. [Architecture Overview](#architecture-overview)

- [Microservice Responsibilities](#microservice-responsibilities)
    - [shared Module](#shared-module)
    - [vpr-service](#vpr-service)
    - [audit-service](#audit-service)

5. [Prerequisites](#prerequisites)
6. [Installation and Usage](#installation-and-usage)

- [Build the Project](#1-build-the-project)
- [Start the Application](#2-start-the-application)
- [Stop and Clean Services](#3-stop-and-clean-services)
- [Running Stress Tests](#4-running-stress-tests)

7. [Database Schema](#database-schema)
8. [API Documentation](#api-documentation)
9. [Future Scope](#future-scope)
10. [Contact and Contributions](#contact-and-contributions)

---

## Overview

The VPR Platform is a Spring Boot-based application designed for the distributed management of updated Republican data
on congenital malformations. It features multiple microservices, including:

- **Core Application (`vpr-service`)**: Handles domain logic, offers REST APIs for CRUD operations, and implements
  secure JWT authentication. It features role-based access control with roles such as **ADMIN**, **DOCTOR**, and **VIEWER**.
- **Audit Microservice (`audit-service`)**: A Kafka-backed service that records system events, such as logins, updates,
  and registrations, in an audit log database.

---

## Key Features

- **Role-Based Access Control**:
    - `ADMIN`: Full access, including user management.
    - `DOCTOR`: Access to domain-specific APIs with limited permissions.
    - `VIEWER`: Default role with view-only permissions.
- **Audit Logs**:
    - Tracks key system events via Kafka.
    - Logs messages into a PostgreSQL audit database.
- **Secure Authentication**:
    - JWT-based authentication for APIs.
    - Only administrators can assign roles to users.
- **Scalable Event Architecture**:
    - Provides a modular Kafka-based platform for asynchronous communication between services.

---

## Tech Stack

- **Programming Language**: Java 17
- **Framework**: Spring Boot 3
- **Event Streaming**: Apache Kafka (Bitnami, KRaft mode)
- **Databases**:
    - PostgreSQL 13.3 for `vpr-service`
    - PostgreSQL 15 for `audit-service`
- **Containerization**: Docker Compose
- **Security**: JWT-based authentication
- **Build Tool**: Maven (Multi-module project)

---

## Architecture Overview

The VPR Platform consists of modular microservices interacting through Kafka:

- **Kafka (Event Bus)**:
    - Handles the communication between services using event-driven architecture.
    - `vpr-service` publishes `AuditEvent` messages to the Kafka `audit-log` topic.
    - `audit-service` subscribes to the topic, consuming and storing these events in an audit database.

### Microservice Responsibilities

#### **shared module**

- Shared Maven module containing reusable components such as:
    - `AuditEvent`: The Kafka message payload for audit logs.
- Included as a dependency in both `vpr-service` and `audit-service`.

#### **vpr-service**

- Main Spring Boot application.
- Key Responsibilities:
    - Exposes REST APIs for CRUD operations and domain logic.
    - Implements JWT-based authentication and role management.
    - Publishes `AuditEvent` messages to Kafka during key actions, such as:
        - User login
        - Registration events
        - Data changes
    - Stores domain data in a PostgreSQL database.

#### **audit-service**

- Microservice to process and store audit logs.
- **Key Responsibilities**:
    - Kafka consumer that subscribes to the `audit-log` topic.
    - Deserializes and stores `AuditEvent` messages in an **audit PostgreSQL 15** database.
    - Designed for future extensibility with a REST API to query and expose audit logs.

---

## Prerequisites

Ensure the following dependencies are installed:

1. **JDK 17**: For running the application.
2. **Maven 3**: For building the project.
3. **Docker**: For running the services in containers.
4. **Environment Variables**: Add a `.env` file based on `env_example.txt`.

---

## Installation and Usage

### Clone Repo:

```bash
git clone https://github.com/MaryiaSinkouskaya/VPR.git
```

### 1. Build the Project

Run the following Maven command to clean, package, and build the project:

```bash
mvn clean install
```

### 2. Start the Application

Run all services (including `vpr-service`, `audit-service`, Kafka, and PostgreSQL) with Docker Compose:

```bash
docker compose up
```

### 3. Stop and Clean Services

Stop services and clean Docker containers and volumes:

```bash
docker compose down --volumes && docker rmi vpr-service audit-service
```

### 4. Running Stress Tests

Run stress tests with the following command:

```bash
mvn test -Dload.test=true
```

---

## Database Schema

The VPR application's database has the following structure:

- **PROBAND**: Description of probands.
- **ABNORMALITY**: List of known abnormalities.
- **MOTHER**: Information about the mother (e.g., age at diagnosis, date of last menstruation).
- **PERSON_INFO**: Unified information about individuals (e.g., mother, father).
- **ADDRESS**: Addresses, such as residences or organizations.
- **WORKPLACE**: Details about parents' workplaces.
- **PROB_D**: Information on whether the proband is deceased.
- **ORGANIZATION**: Details about clinics or healthcare organizations.
- **DOCTOR**: Information about healthcare providers.
- **NOTE**: Additional, free-form notes.

![Database Schema](schema.png)

---

## API Documentation

Access the OpenAPI (Swagger) documentation at the following URL:
`${vpr.openapi.dev-url}/swagger-ui/index.html#/`