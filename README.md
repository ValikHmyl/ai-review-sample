# task-manager (ai-review-sample)

A small Spring Boot REST API for managing users and tasks. This repository is used as
a **review target**: it is the base codebase against which pull requests are opened, so
that an MCP-based Code Review Agent can retrieve PR metadata, changed files/diffs, and
code context via GitHub MCP tools, analyze the changes, and publish inline review
comments plus an overall summary.

## Stack

- Java 17, Spring Boot 3.2, Maven
- Spring Data JPA + H2 (in-memory, dev only)
- Bean Validation (`jakarta.validation`)

## Project layout

```
src/main/java/com/example/taskmanager/
  controller/   REST endpoints (Task, User)
  service/      business logic
  repository/   Spring Data JPA repositories
  model/        JPA entities
  dto/          request/response payloads
  exception/    custom exceptions + global handler
```

## Running locally

```bash
./mvnw spring-boot:run
```

The API listens on `http://localhost:8080`:

- `GET /api/tasks`, `GET /api/tasks/{id}`, `POST /api/tasks`, `PUT /api/tasks/{id}`, `DELETE /api/tasks/{id}`
- `GET /api/users`, `GET /api/users/{id}`, `POST /api/users`

## Tests

```bash
./mvnw test
```
