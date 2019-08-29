FROM java:8-jdk-alpine
EXPOSE 8085
ADD /target/simple-game-0.0.1-SNAPSHOT.war simple-game-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java","-jar","simple-game-0.0.1-SNAPSHOT.war"]