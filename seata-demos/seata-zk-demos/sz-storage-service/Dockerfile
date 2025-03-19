FROM ghcr.io/opcal/eclipse-temurin:21-jre

WORKDIR /app

COPY ./target/sz-storage-service.jar /app

EXPOSE 20384

CMD ["java", "-jar", "/app/sz-storage-service.jar"]
  