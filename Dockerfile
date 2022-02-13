FROM openjdk:17
ARG JAR_FILE=build/libs/mortagePlan-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
COPY prospects.txt prospects.txt
ENTRYPOINT ["java","-jar","/app.jar"]