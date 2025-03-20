FROM ghcr.io/opcal/eclipse-temurin:21-jre

WORKDIR /app

COPY ./target/sc-account-service.jar /app

EXPOSE 30383

CMD ["java", "-jar", "/app/sc-account-service.jar"]
  