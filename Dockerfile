FROM frolvlad/alpine-java
RUN apk add -U tzdata
RUN cp /usr/share/zoneinfo/America/Santiago /etc/localtime
RUN date
RUN mkdir -p /prueba/lib/
RUN mkdir -p /prueba/config/
RUN mkdir -p /prueba/logs/
ADD src/main/resources/application.properties /prueba/config
WORKDIR /prueba
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","lib/app.jar"]
EXPOSE 9091
ADD target/gs-spring-boot-docker-0.1.0.jar /prueba/lib/app.jar
