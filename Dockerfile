FROM store/oracle/jdk:11

COPY build/libs/basic-spring-boot-application-0.0.1-SNAPSHOT.jar /opt/app/bsbs.jar

WORKDIR /opt/app

EXPOSE 8080

CMD ["java", "-jar", "/opt/app/bsbs.jar"]