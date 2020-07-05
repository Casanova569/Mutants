# Mutants

## Table of contents
1. [Introduction](#introduction)
2. [Exercise](#exercise)
    1. [Problem requested](#problem-requested)
    2. [Mutant example](#mutant-example)
    3. [Challenge](#challenge)
        1. [Level 1](#level-1)
        2. [Level 2](#level-2)
        3. [Level 3](#level-3)
    4. [Deliver](#deliver)
3. [Technologies](#technologies)
    1. [Language](#language)
        1. [SDKMAN](#sdk)
    2. [Testing](#testing)
    3. [Swagger](#swagger)
    4. [SonarLint](#sonar)
    5. [Database](#database)
        1. [PostgresSQL](#postgressql)
        2. [Hibernate](#hibernate)
        3. [PgAdmin4](#pgadmin4)
    6. [Deploy](#deploy)
    7. [Postman](#postman)
    
## <a name="introduction"></a>Introduction
The purpose of this repository is to fulfill the exam of Mercado Libre.

## <a name="exercise"></a>Exercise

### <a name="problem-requested"></a> Problem requested
Magneto wants to recruit as many mutants as possible to fight
against the X-Men.
He has hired you to develop a project that detects if a human is mutant based on its DNA sequence.
For this purpose he asked you to create a program with a method or function with the following signature (In any of the following languages: Java / Golang / C-C ++ / Javascript (node) / Python / Ruby):
```
boolean isMutant(String[] dna); // Java example
```
Where you will receive as parameter an array of Strings that represent each row of a table of (NxN) with the DNA sequence. The letters of the Strings can only be: (A, T, C, G). Those characters represents each nitrogenous base of the DNA.
You will know if a human is mutant, if you find more than one sequence of four equal letters, obliquely, horizontally or vertically.

### <a name="mutant-example"></a> Mutant example
```
String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
```
In this case the call to the function isMutant (dna) returns "true".
Develop the algorithm in the most efficient way possible and good luck.

### <a name="challenge"></a> Challenge

#### <a name="level-1"></a> Level 1
Program (in any programming language) that complies with the method requested by Magneto.

#### <a name="level-2"></a> Level 2
Create a REST API, host that API in a free cloud computing (Google App Engine, Amazon AWS, etc). Create the service "/ mutant /" where it can be detected if a human is a mutant by sending the DNA sequence via HTTP POST with a Json which has the following format:
```
POST → /mutant/
{
“dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
```
If you successfully verify a mutant, it should return an HTTP 200-OK, otherwise a 403-Forbidden.

#### <a name="level-3"></a> Level 3
Attach a database, which stores the DNA's verified with the API.
Only 1 DNA record.
Expose an extra service "/ stats" that returns a Json with the DNA verification statistics: {"count_mutant_dna": 40, "count_human_dna": 100: "ratio": 0.4}
Keep in mind that the API can receive aggressive traffic fluctuations (Between 100 and 1 million requests per second).
Test-Automatic, Code coverage> 80%.

### <a name="deliver"></a> Deliver
1. Source Code (For Level 2 and 3: In github repository).
2. Instructions on how to run the program or API. (For Level 2 and 3: In github README).
3. API URL (Level 2 and 3).

## <a name="technologies"></a> Technologies

### <a name="language"></a> Language
The language selected to do this exercise in this repository is Java version 11.
Will be a spring boot application with gradle.

#### <a name="sdk"></a> SDKMAN
SDKMAN is a tool for managing parallel versions of multiple Software Development Kits on most Unix based systems.
It provides a convenient Command Line Interface (CLI) and API for installing, switching, removing and listing Candidates.
It is used for install 11.0.7-zulu java version in this project.

### <a name="testing"></a> Testing
Used JUnit for testing the project. Will be testing all the services and controllers (100%).

### <a name="swagger"></a>Swagger
Swagger is an open-source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful web services.
If you want to saw swagger ui with this project you need to run the project and go to: 
```
http://localhost:8081/swagger-ui.html#/
```

### <a name="sonar"></a> SonarLint
SonarLint is an IDE extension that helps you detect and fix quality issues as you write code.
Like a spell checker, SonarLint squiggles flaws so that they can be fixed before committing code.
You can add it in Intellij plugins.

### <a name="database"></a> Database

#### <a name="postgressql"></a> PostgresSQL
It is used PostgresSQL as the database engine.

You can download from this link: https://www.postgresql.org/download/

#### <a name="hibernate"></a> Hibernate
Hibernate ORM is an object-relational mapping tool for the Java programming language. It provides a framework for mapping an object-oriented domain model to a relational database.
For those motives it is used for persistence with postgresSQL database.

#### <a name="pgadmin4"></a> PgAdmin 4
PgAdmin is the leading Open Source management tool for Postgres databases.

You can install and learn more from the official page: https://www.pgadmin.org/download/pgadmin-4-apt/ 

### <a name="deploy"></a> Deploy
It is used Heroku as cloud platform.

The URL's are:
- https://mutant-test-x.herokuapp.com/mutant/
- https://mutant-test-x.herokuapp.com/mutant/stats

### <a name="postman"></a> Postman
It is used to test the endpoints in heroku.

You can download from the official page: https://www.postman.com/downloads/

To test you need to send a request. For example:

- To test https://mutants-x.herokuapp.com/mutant/
You need to do a post request with the follow json body:
```
{
	"dna": ["AAAAGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
```
- For test https://mutants-x.herokuapp.com/mutant/stats you only need to send a GET request.
