FROM ghcr.io/opcal/eclipse-temurin:21-jre

WORKDIR /app

COPY ./target/sc-order-service.jar /app

EXPOSE 30385

CMD ["java", "-jar", "/app/sc-order-service.jar"]
  