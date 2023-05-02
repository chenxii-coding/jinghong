#syntax=docker/dockerfile:1
FROM java:8
WORKDIR /app
COPY target/jinghong-server-v1.1.0.jar app.jar
CMD ["java", "-jar", "app.jar"]
EXPOSE 8000
