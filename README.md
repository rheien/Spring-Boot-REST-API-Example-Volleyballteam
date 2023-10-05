# Spring-Boot-REST-API-Example-Volleyballteam
This application will explore how to create REST APIs for CRUD operations using Spring Boot framework.

To start the application, run the following command in your terminal:
``` 
mvn spring-boot:run
```

## How to use the application

With the application running, open your browser and navigate to http://localhost:8080/swagger-ui/index.html. 
You will see the Swagger UI page from where you can test the REST APIs.

Of course, you can also use Postman or any other REST client to test the APIs.
The REST API can be accessed at http://localhost:8080/api/teams.

Following endpoints are available in the application:
- GET /api/teams - Get all teams
- GET /api/teams/{name} - Get team by name
- GET /api/teams/players - Get all players
- GET /api/teams/players/{id} - Get player by id
- GET /api/teams/{name}/players - Get all players by team name
- POST /api/teams/team-management - Create or update a team 
- POST /api/teams/team-management/players - Add a player to a team
- DELETE /api/teams/team-management/players - Remove a player from a team
