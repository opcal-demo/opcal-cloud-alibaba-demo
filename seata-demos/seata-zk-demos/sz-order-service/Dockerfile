FROM ghcr.io/opcal/eclipse-temurin:21-jre

WORKDIR /app

COPY ./target/sz-order-service.jar /app

EXPOSE 20385

CMD ["java", "-jar", "/app/sz-order-service.jar"]
  