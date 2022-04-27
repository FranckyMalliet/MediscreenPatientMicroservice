FROM openjdk:11
EXPOSE 80
VOLUME /tmp
ARG JAR_FILE=microservice-patient/target/microservicePatient.jar
COPY ${JAR_FILE} microservicePatient.jar
ENTRYPOINT ["java","-jar","/microservicePatient.jar"]