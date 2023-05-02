FROM maven
WORKDIR /app
RUN mvn clean && mvn package
ADD target/jinghong-config-v1.1.0.jar app.jar
CMD ["java", "-jar", "app.jar"]
EXPOSE 8001
