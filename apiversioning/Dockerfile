FROM openjdk:11.0-jdk-slim
RUN mkdir app
COPY ./target/apiversioning*.jar /app/apiversioning.jar
WORKDIR /app/
EXPOSE 8080
USER 1001
ENTRYPOINT ["java","-jar","apiversioning.jar"]