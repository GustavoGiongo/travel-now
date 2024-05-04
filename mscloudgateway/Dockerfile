FROM maven:3.8.5-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar
ARG KEYCLOAK_SERVER=keycloak
ARG KEYCLOAK_PORT=8081
ARG EUREKA_SERVER=ms-eureka
ENTRYPOINT java -jar app.jar

