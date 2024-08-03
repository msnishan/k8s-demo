FROM registry.hub.docker.com/library/openjdk:17-slim

WORKDIR /app

COPY build/libs/k8s-demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]