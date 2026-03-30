FROM  maven:3.9.6-eclipse-temurin-21 AS build
LABEL authors="jerep"

WORKDIR /app

COPY pom.xml .

COPY . /app

RUN mvn package

CMD ["java", "-jar", "target/UI_Localization_Assignment-1.0-SNAPSHOT.jar"]