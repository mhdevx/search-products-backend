FROM maven:3.5-jdk-8 AS build  
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package

FROM gcr.io/distroless/java  
COPY --from=build /usr/src/app/target/gs-spring-boot-docker-0.1.0.jar /usr/app/gs-spring-boot-docker-0.1.0.jar  
EXPOSE 9091
ENTRYPOINT ["java","-jar","/usr/app/gs-spring-boot-docker-0.1.0.jar"]  