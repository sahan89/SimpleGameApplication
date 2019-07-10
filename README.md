## *Overview*
Simple Game Application assignment for NetEnt.

## *Prerequisites*
* Java 1.8
* Maven
* MySQL
* Spring Boot
* Hibernate
* JUnit
* Swagger ```http://localhost:8085/swagger-ui.html```

## *Build Project*
1. Create Database ```CREATE DATABASE simple_game_db;``` (Create Database automatically) 
2. Set Username and Password in the ```application.yml``` file
3. Clone the project
4. Invoke ```mvn clean install -DskipTests```
5. Navigate to target folder

## *Run Project*

Play Game ```http://localhost:8085/rest/gamePlayController/normalGameRound```

Rest API body
`coins = betting amount`
`gameType = Normal round game`

`{
   	"coins" : 10,
        "gameType" :1
   }`

Header
`Content-Type application/json`

Get winning amount of previous games
`http://localhost:8085/rest/resultController/result?uniqueId=001`
