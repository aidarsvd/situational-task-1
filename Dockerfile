FROM maven:3.9.5 AS build

WORKDIR fin_tech_innovators

COPY src ./src
COPY pom.xml .

RUN mvn clean package -DskipTests

FROM openjdk:17

COPY --from=build fin_tech_innovators/target/fintech.jar fintech.jar

ENTRYPOINT ["java", "-jar", "fintech.jar"]