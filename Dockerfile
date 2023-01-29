FROM maven:3.8.5-openjdk-17-slim AS builder
WORKDIR /home/app
COPY pom.xml .
COPY src ./src
RUN mvn -f ./pom.xml package

FROM openjdk:17-alpine
WORKDIR /usr/local/lib
COPY --from=builder /home/app/target/teste-java-1.0.0-jar-with-dependencies.jar ./teste-java.jar
CMD [ "java", "-jar", "/usr/local/lib/teste-java.jar" ]