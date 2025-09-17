\# 🏋️ RESTful Webservice \& Microservices Practice with Spring Boot



\## 📌 Introduction

This repository provides a set of \*\*hands-on exercises\*\* to practice building \*\*RESTful APIs\*\* and \*\*Microservices\*\* using Spring Boot.  

The goal is to move from \*\*Intermediate (Level 2)\*\* to \*\*Advanced (Level 3)\*\* skills, covering API design, service-to-service communication, security, API Gateway, Service Discovery, and event-driven architecture.  



---



\## 📂 Exercises



\### 🔹 Level 2 – Advanced RESTful Services (Standalone Services)



\#### Exercise 1: CRUD with Validation \& Exception Handling

\- Build `UserService` for user management.

\- Requirements:

&nbsp; - Input validation (valid email, password length ≥ 8).

&nbsp; - Exception handling (404 if user not found, 400 if invalid input).

&nbsp; - API documentation using Swagger/OpenAPI.



\#### Exercise 2: Pagination \& Filtering

\- Build `ProductService` for product management.

\- Example API:

GET /products?page=1\&size=10\&category=phone\&priceMin=1000\&priceMax=2000

\- Response must include metadata (totalPages, totalItems, currentPage).



\#### Exercise 3: API Versioning

\- Build `OrderService`.

\- `/api/v1/orders`: basic order info.

\- `/api/v2/orders`: detailed info (product list, total price).



\#### Exercise 4: Caching

\- Add caching for `ProductService` using \*\*ETag / Cache-Control\*\*.

\- Goal: reduce unnecessary API calls.



---



\### 🔹 Level 3 – Microservices \& Integration



\#### Exercise 5: Authentication with JWT

\- Implement `AuthService` for user registration \& login.

\- Login returns JWT.

\- Other services (`UserService`, `OrderService`) must validate token.



\#### Exercise 6: Service-to-Service Communication

\- `OrderService` checks product stock via `ProductService`.

\- Use \*\*FeignClient\*\* or \*\*WebClient\*\* for inter-service communication.



\#### Exercise 7: API Gateway

\- Use \*\*Spring Cloud Gateway\*\*.

\- Single entry point `/api/...` that routes requests to different services.

\- Add JWT validation filter in the Gateway.



\#### Exercise 8: Service Discovery

\- Use \*\*Eureka\*\* or \*\*Consul\*\*.

\- Services (`UserService`, `OrderService`, `ProductService`) auto-register.

\- Gateway resolves services dynamically via registry.



\#### Exercise 9: Event-driven with Kafka

\- When an order is created in `OrderService`, publish an `OrderCreated` event.

\- `NotificationService` consumes the event and sends an email to the user.



\#### Exercise 10: Monitoring \& Logging

\- Log all requests/responses.

\- Add \*\*Spring Boot Actuator\*\* for health checks.

\- (Advanced) Integrate with \*\*ELK Stack\*\* for logs and \*\*Prometheus/Grafana\*\* for metrics.



---



\## 🏗️ Overall Architecture



```mermaid

flowchart TD

&nbsp; Client((Client)) --> Gateway\[API Gateway]

&nbsp; Gateway --> AuthService\[Auth Service]

&nbsp; Gateway --> UserService\[User Service]

&nbsp; Gateway --> ProductService\[Product Service]

&nbsp; Gateway --> OrderService\[Order Service]



&nbsp; OrderService -->|check stock| ProductService

&nbsp; OrderService -->|publish event| Kafka\[(Kafka Broker)]

&nbsp; Kafka --> NotificationService\[Notification Service]



&nbsp; subgraph Infra

&nbsp;     Eureka\[Service Discovery]

&nbsp;     ELK\[ELK Stack / Logging]

&nbsp;     Prometheus\[Prometheus/Grafana]

&nbsp; end



&nbsp; UserService --> Eureka

&nbsp; ProductService --> Eureka

&nbsp; OrderService --> Eureka

&nbsp; AuthService --> Eureka

&nbsp; Gateway --> Eureka



&nbsp; UserService --> ELK

&nbsp; ProductService --> ELK

&nbsp; OrderService --> ELK

&nbsp; AuthService --> ELK

&nbsp; Gateway --> ELK



&nbsp; UserService --> Prometheus

&nbsp; ProductService --> Prometheus

&nbsp; OrderService --> Prometheus

&nbsp; AuthService --> Prometheus

&nbsp; Gateway --> Prometheus



