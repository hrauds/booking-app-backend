# Game Time Booking Application

## Introduction

This application allows businesses that offer various entertainment options to allow customers to book
any of their resources online. That is, clients with a link to the booking page can choose what they
want to book, and the business can provide whatever they want for booking.

### Authors

- Hanna Raudsepp (hrauds@taltech.ee)
- Ignat Jasevits (igjase@taltech.ee)

### Technologies Used

- Java 17
- Spring Boot 3.1.3
- PostgreSQL 16
- MapStruct 1.5.5
- Spring Security 6.2.0
- Liquibase 4.23.0
- Lombok 1.18.30
- Springdoc-OpenApi 1.6.12

## Setting up the development environment

These instructions will help you set up the project on your local machine for development and testing purposes.

### Prerequisites

- Java version 17
- PostgreSQL version 16 (Database running)

### Setup

Clone the repository:

`git clone https://gitlab.cs.taltech.ee/hrauds/iti0302-2023-backend.git`

Navigate to the project directory:

`cd [project folder]`

Locate `application.properties` in the `src/main/resources/`  directory and fill it with the correct database connection data and other necessary configurations.

Build the project (this will also download the necessary dependencies):

`mvn clean install`

Run the application:

`mvn spring-boot:run`

