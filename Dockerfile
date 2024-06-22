FROM openjdk:17-jdk-alpine
LABEL authors="Malek"

COPY /build/libs/stock-management-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]