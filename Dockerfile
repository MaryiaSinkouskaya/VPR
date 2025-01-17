FROM openjdk:17
MAINTAINER c5329198
COPY target/VPR-1.0-SNAPSHOT.jar app.jar
CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]