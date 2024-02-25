# dice-game
# Getting Started
JDK-11
mvn clean package

## Build the Docker image using
docker build -t test .
## Run the Docker container using
docker run -p 8080:8080 test.

## swagger-ui
http://localhost:8088/swagger-ui.html