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

<details>
<summary><code>GET</code> <code>http://localhost:8080/api/teams</code></summary>

As a curl command:
```bash
curl --location 'http://localhost:8080/api/teams'
```

Get all teams in response as JSON:
```bash
[
    {
        "name": "Füchse",
        "trainer": "Hermann",
        "location": "Berlin",
        "players": [
            {
                "id": "1",
                "name": "Hans",
                "position": "Libero"
            }
        ]
    },
    {
        "name": "Dolphin",
        "trainer": "Joos",
        "location": "Stuttgart",
        "players": []
    }
]
```
</details>

<details>
<summary><code>GET</code> <code>http://localhost:8080/api/teams/{name}</code></summary>

As a curl command:
```bash
curl --location 'http://localhost:8080/api/teams/Füchse'
```

Get a team by name in response as JSON:
```bash
{
    "name": "Füchse",
    "trainer": "Hermann",
    "location": "Berlin",
    "players": [
        {
            "id": "1",
            "name": "Hans",
            "position": "Libero"
        }
    ]
}
```
</details>

<details>
<summary><code>GET</code> <code>http://localhost:8080/api/teams/players</code></summary>

As a curl command:
```bash
curl --location 'http://localhost:8080/api/teams/players'
```

Get all players in response as JSON:
```bash
[
    {
        "id": "1",
        "name": "Hans",
        "position": "Libero"
    }
]
```
</details>

<details>
<summary><code>GET</code> <code>http://localhost:8080/api/teams/players/{id}</code></summary>

As a curl command:
```bash
curl --location 'http://localhost:8080/api/teams/players/1'
```

Get a player by id in response as JSON:
```bash
{
    "id": "1",
    "name": "Hans",
    "position": "Libero"
}
```
</details>

<details>
<summary><code>GET</code> <code>http://localhost:8080/api/teams/{name}/players</code></summary>

As a curl command:
```bash
curl --location 'http://localhost:8080/api/teams/Füchse/players'
```

Get all players by team name in response as JSON:
```bash
[
    {
        "id": "1",
        "name": "Hans",
        "position": "Libero"
    }
]
```
</details>

<details>
<summary><code>POST</code> <code>http://localhost:8080/api/teams/team-management</code></summary>

As a curl command:
```bash
curl --location --request POST 'http://localhost:8080/api/teams/team-management' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Feuer",
    "trainer": "Müller",
    "location": "Hannover"
}'
```

Create or update a team in response as JSON:
```bash
{
    "name": "Feuer",
    "trainer": "Müller",
    "location": "Hannover",
    "players": []
}
```
</details>

<details>
<summary><code>POST</code> <code>http://localhost:8080/api/teams/team-management/players</code></summary>

As a curl command:
```bash
curl --location --request POST 'http://localhost:8080/api/teams/team-management/players' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Jochen",
    "position": "Opposite Hitter",
    "team": {
        "name": "Füchse"
    }
}'
```

Add a player to a team in response as JSON:
```bash
{
    "name": "Füchse",
    "trainer": "Hermann",
    "location": "Berlin",
    "players": [
        {
            "id": "1",
            "name": "Hans",
            "position": "Libero"
        },
        {
            "id": "2",
            "name": "Jochen",
            "position": "Opposite Hitter"
        }
    ]
}
```
</details>

<details>
<summary><code>DELETE</code> <code>http://localhost:8080/api/teams/team-management/players</code></summary>

As a curl command:
```bash
curl --location --request DELETE 'http://localhost:8080/api/teams/team-management/players' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Jochen",
    "position": "Opposite Hitter",
    "team": {
        "name": "Füchse"
    }
}'
```

Remove a player from a team in response as JSON:
```bash
{
    "name": "Füchse",
    "trainer": "Hermann",
    "location": "Berlin",
    "players": [
        {
            "id": "1",
            "name": "Hans",
            "position": "Libero"
        }
    ]
}
```
</details>
