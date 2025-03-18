FROM ghcr.io/opcal/eclipse-temurin:21-jre

WORKDIR /app

COPY ./target/nd-service-a.jar /app

EXPOSE 10380

CMD ["java", "-jar", "/app/nd-service-a.jar"]
  