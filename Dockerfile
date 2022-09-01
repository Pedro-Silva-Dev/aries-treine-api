FROM maven:3.8.2-jdk-11
WORKDIR /aries-treine-api
COPY . .
CMD mvn spring-boot:run