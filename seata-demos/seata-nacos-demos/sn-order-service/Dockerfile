FROM ghcr.io/opcal/eclipse-temurin:21-jre

WORKDIR /app

COPY ./target/sn-order-service.jar /app

EXPOSE 10381

CMD ["java", "-jar", "/app/sn-order-service.jar"]
  