FROM java:8-jdk-alpine

COPY ./target/spring-basic-auth-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch spring-basic-auth-0.0.1-SNAPSHOT.jar'

EXPOSE 8010

ENTRYPOINT ["java", "-jar", "spring-basic-auth-0.0.1-SNAPSHOT.jar"]