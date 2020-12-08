FROM openjdk:8 
ADD /target/vegan-0.0.1-SNAPSHOT.jar vegan-server.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","vegan-server.jar"]