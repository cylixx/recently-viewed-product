FROM adoptopenjdk/openjdk11:latest
COPY build/libs/*.jar product-service.jar
ENTRYPOINT ["java","-jar","/product-service.jar"]
