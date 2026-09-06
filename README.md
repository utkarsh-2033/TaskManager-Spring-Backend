# Task Manager API

A RESTful Task Management API built with **Spring Boot**, **Spring Data JPA**, **PostgreSQL**, **Spring Security**, and **JWT authentication**.

## Features

- User registration and login
- JWT-based authentication
- BCrypt password hashing
- Create, read, update, and delete tasks
- User-specific task ownership
- Task status and priority management
- PostgreSQL database integration
- Stateless authentication with Spring Security

## Tech Stack

- **Java**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security**
- **JWT**
- **BCrypt**
- **PostgreSQL**
- **Maven**

## Project Structure

```text
src/main/java/org/utkarsh/taskmanager
│
├── config
│   ├── JwtFilter.java
│   └── SecurityConfig.java
│
├── controller
│   ├── UserController.java
│   ├── TaskController.java
│   └── Tester.java
│
├── dto
│   ├── RegisterUser.java
│   ├── LoginUser.java
│   ├── CreateTask.java
│   ├── UpdateTask.java
│   └── TasksResponse.java
│
├── model
│   ├── User.java
│   └── Task.java
│
├── repository
│   ├── UserRepo.java
│   └── TasksRepo.java
│
├── service
│   ├── UserService.java
│   ├── TaskService.java
│   ├── JwtService.java
│   └── CustomUserDetailsService.java
│
└── TaskManagerApplication.java
