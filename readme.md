## Application for distributed access to the updated Republican database of congenital malformations

Implemented basic API with CRUD operations via REST. JWT authentication applied. Key roles: ADMIN, DOCTOR, VIEWER.
Only admin can manage other users and create user with any role. After registration the only role assigned is VIEWER.

### Prerequisites:

- Maven 3
- JDK 17
- Docker
- add .env file with JWT_SECRET=your_secret_key inside

### How to build:

    mvn clean install

### Start application

    docker compose up      

### Stop & remove image

    docker compose down --volumes && docker rmi vpr

### Run Stress test

`mvn test -Dload.test=true`

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
