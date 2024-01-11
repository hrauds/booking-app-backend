
#Installation Guide

## Frontend Setup

Clone the repository:
`git clone https://gitlab.cs.taltech.ee/hrauds/iti0302-2023-frontend.git`

Navigate to the project directory:
`cd [project folder]`

Install the dependencies:
```sh
npm install
```


Set up the development environment:
```sh
npm run dev
```

## Backend Setup
### Prerequisites
- Java version 17
- PostgreSQL version 16 (Database running)
- Docker

### Setup

Clone the repository:

`git clone https://gitlab.cs.taltech.ee/hrauds/iti0302-2023-backend.git`

Navigate to the project directory:

`cd [project folder]`

Locate `application.properties` in the `src/main/resources/` directory and `docker-compose.yml`. Then fill them with the correct database connection information and other necessary configurations.

Build the project (this will also download the necessary dependencies):

`mvn clean install`

Run `docker-compose up -d`

Run the application:

`mvn spring-boot:run`
