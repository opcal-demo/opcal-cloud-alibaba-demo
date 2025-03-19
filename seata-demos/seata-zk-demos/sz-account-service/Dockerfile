FROM ghcr.io/opcal/eclipse-temurin:21-jre

WORKDIR /app

COPY ./target/sz-account-service.jar /app

EXPOSE 20383

CMD ["java", "-jar", "/app/sz-account-service.jar"]
  