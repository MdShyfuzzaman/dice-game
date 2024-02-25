FROM openjdk:11
COPY target/test-0.0.1-SNAPSHOT.jar test-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "test-0.0.1-SNAPSHOT.jar"]
