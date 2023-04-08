FROM openjdk:17-alpine

COPY bots-1.0-SNAPSHOT.jar bots-1.0-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/bots-1.0-SNAPSHOT.jar"]