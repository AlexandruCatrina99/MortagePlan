# MortagePlan
Kodtest för Crosskey

Use all the commands in Command Prompt (not PowerShell)
To run the aplication use the commands:
gradlew clean
gradlew build
gradlew bootRun



To create a docker image:
Step1:
Build the gradle application:
gradlew build && java -jar build/libs/mortage-plan-0.1.0.jar

Step 2:
Build the dockerimage:
docker build --build-arg JAR_FILE=build/libs/*.jar -t springio/mortageplan .

Now we have a docker image and can run it to create containers


To make a get all prospects:
http://localhost:8080/prospects/
To add a new prospect:
http://localhost:8080/prospects/CustomerName/Loan/Interest/Years