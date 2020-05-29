FROM java:8-jdk-alpine

COPY ./target/spring-form-auth-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch spring-form-auth-0.0.1-SNAPSHOT.jar'

EXPOSE 8100

ENTRYPOINT ["java", "-jar", "spring-form-auth-0.0.1-SNAPSHOT.jar"]