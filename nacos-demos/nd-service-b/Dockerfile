FROM ghcr.io/opcal/eclipse-temurin:21-jre

WORKDIR /app

COPY ./target/nd-service-b.jar /app

EXPOSE 10381

CMD ["java", "-jar", "/app/nd-service-b.jar"]
  