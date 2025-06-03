FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY ./pom.xml ./


COPY ./src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY --from=build /app/target/fx-deals-importer-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8081
CMD ["java", "-jar", "app.jar"]
