FROM ghcr.io/opcal/eclipse-temurin:21-jre

WORKDIR /app

COPY ./target/sc-storage-service.jar /app

EXPOSE 30384

CMD ["java", "-jar", "/app/sc-storage-service.jar"]
  