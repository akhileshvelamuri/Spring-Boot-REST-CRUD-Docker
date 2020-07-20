FROM openjdk:8
ADD target/docker-spring-boot-rest-crud.jar docker-spring-boot-rest-crud.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "docker-spring-boot-rest-crud.jar"]