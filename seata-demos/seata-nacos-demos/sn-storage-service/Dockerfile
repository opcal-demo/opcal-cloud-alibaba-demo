FROM ghcr.io/opcal/eclipse-temurin:21-jre

WORKDIR /app

COPY ./target/sn-storage-service.jar /app

EXPOSE 10384

CMD ["java", "-jar", "/app/sn-storage-service.jar"]
  