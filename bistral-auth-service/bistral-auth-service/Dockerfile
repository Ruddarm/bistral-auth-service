FROM  eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY target/bistral-auth-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8086

ENTRYPOINT ["java","-jar","app.jar"]
