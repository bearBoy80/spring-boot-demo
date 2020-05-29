FROM java:8-jdk-alpine

COPY ./target/spring-actuator-auth-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch spring-actuator-auth-0.0.1-SNAPSHOT.jar'

EXPOSE 8100

ENTRYPOINT ["java", "-jar", "spring-actuator-auth-0.0.1-SNAPSHOT.jar"]