FROM ghcr.io/opcal/eclipse-temurin:21-jre

WORKDIR /app

COPY ./target/sn-account-service.jar /app

EXPOSE 10383

CMD ["java", "-jar", "/app/sn-account-service.jar"]
  