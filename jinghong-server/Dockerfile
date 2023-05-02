#syntax=docker/dockerfile:1
FROM ascdc/jdk8
WORKDIR /app
COPY target/jinghong-server-1.1.0.jar app.jar
CMD ["java", "-jar", "app.jar"]
EXPOSE 8001
