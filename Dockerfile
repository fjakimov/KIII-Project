FROM openjdk:17-jdk-slim

COPY target/lab-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 9090

CMD ["java", "-jar", "/app/app.jar"]