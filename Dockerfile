FROM openjdk:17
MAINTAINER c5329198
COPY target/VPR-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]