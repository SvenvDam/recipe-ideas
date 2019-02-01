# Recipe ideas

_This tool will help answering the question `what to eat tonight?`._

The main idea is to store recipes in a graph database by their connections with ingredients.
This allows to calculate similarities between recipes.
Not only can you then pass a list of ingredients and find recipes that are similar to it,
you can also provide the ingredients of what you ate yesterday and use them to find something dissimilar.

This helps you to come up with ideas for dinner without falling into a loop of  similar recipes.

**What this tool is**

This is a tool aimed at home cooks who do not use recipes as a strict guidance but rather have them for inspiration.

**What this tool is not**

It is not a recipe organizer where you can store your recipes in a structured way and query them.
Nor is it a tool which helps you follow the recipe instructions.

### Instructions

#### Build
```bash
script/build
```
This will package the application and build Docker images

#### Run
```bash
script/run
```
This will run the docker compose with all services

#### Stop
```bash
script/kill
```

#### View project in browser
```bash
script/browser
```

#### Run tests
```bash
script/test
```


### Architecture

The project will consist of three components
* **Database:** a neo4j instance storing recipes and ingredients
* **Back-end:** an akka-based application translating the db connectivity into a REST interface
* **Front-end:** a flask-based web app to interact with the data through your browser