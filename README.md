## dice-game
## Tools and Technologies
- JDK-11
- Spring Boot 2.7.14
- Apache Maven 3.6.3 

## Build Application
mvn clean install

## Run Application
mvn spring-boot:run

## Build the Docker image using
docker build -t dice-game-app .
## Run the Docker container using
docker run -it -p 8080:8000 dice-game-app

## swagger-ui
http://localhost:8088/swagger-ui.html

## api documentation
http://localhost:8088/v2/api-docs

## Request sample (you can hit from swagger UI)
### Create Player
curl -X POST "http://localhost:8088/create/player" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"age\": 10, \"name\": \"Mr. John\", \"startRolling\": true, \"totalScore\": 0}"

### Play Game
curl -X POST "http://localhost:8088/play/game" -H "accept: */*"

### Get Scores
curl -X GET "http://localhost:8088/scores"

### Get Winner Name
curl -X GET "http://localhost:8088/winner"

### Reset The Game
curl -X GET "http://localhost:8088/start"