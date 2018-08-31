FROM openjdk:8-jre-alpine
COPY ./target/UserBackendService-0.0.1-SNAPSHOT.jar /usr/src/userservice/
WORKDIR /usr/src/userservice/
EXPOSE 9009
CMD ["java","-jar","UserBackendService-0.0.1-SNAPSHOT.jar"]