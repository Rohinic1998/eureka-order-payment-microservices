# Eureka Order Payment Microservices

A simple Spring Boot microservices project demonstrating **Service Discovery using Eureka** and **inter-service communication** between Order and Payment services.

This project is designed as an **interview-ready microservices example** using:

* Spring Boot
* Spring Cloud Eureka
* REST communication using RestTemplate
* MySQL database
* Layered architecture

---

## Project Overview

This project consists of three services:

1. **Service Discovery (Eureka Server)**

   * Registers and monitors all microservices
   * Runs on port **8761**

2. **Order Service**

   * Handles order-related operations
   * Registers with Eureka as **order**
   * Runs on port **8080**

3. **Payment Service**

   * Calls Order service using Eureka service name
   * Registers with Eureka as **payment**
   * Runs on port **8090**

---

## Architecture Flow

Client → Payment Service → Eureka → Order Service → Response

---

## Project Structure

```
eureka-order-payment-microservices
│
├── service-discovery
│   ├── src/main/java/com/service
│   └── application.properties
│
├── order-service
│   ├── src/main/java/com/order
│   │   ├── controller
│   │   ├── service
│   │   ├── repository
│   │   └── entity
│   └── application.properties
│
├── payment-service
│   ├── src/main/java/com/payment
│   │   ├── controller
│   │   ├── config
│   │   └── entity
│   └── application.properties
│
└── README.md
```

---

## Technologies Used

* Java 21
* Spring Boot
* Spring Cloud Netflix Eureka
* Spring Data JPA
* MySQL
* Maven
* RestTemplate

---

## Configuration

### 1. Eureka Server (`service-discovery`)

**application.properties**

```
server.port=8761
spring.application.name=service-discovery

eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

---

### 2. Order Service (`order-service`)

**application.properties**

```
spring.application.name=order
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/microservices_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

---

### 3. Payment Service (`payment-service`)

**application.properties**

```
spring.application.name=payment
server.port=8090

spring.datasource.url=jdbc:mysql://localhost:3306/microservices_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

---

## How to Run the Project

Start services in this order:

1. service-discovery
2. order-service
3. payment-service

Open Eureka dashboard:

```
http://localhost:8761
```

You should see:

```
ORDER
PAYMENT
```

---

## API Endpoints

### 1. Create Order

**POST**

```
http://localhost:8080/api/v1/order
```

**Request Body**

```json
{
  "productName": "Laptop",
  "quantity": 2,
  "price": 50000
}
```

---

### 2. Get Order by ID

**GET**

```
http://localhost:8080/api/v1/order/1
```

---

### 3. Payment Service calling Order via Eureka

**GET**

```
http://localhost:8090/payment/api/v1/orderDetails/1
```

---

## Postman Testing Steps

1. Start all three services.
2. Create an order using the Order service.
3. Note the order ID.
4. Call the Payment service endpoint.
5. Payment service fetches order details from Order service via Eureka.

---

## Interview Explanation (Short Script)

This project demonstrates a basic microservices architecture using Spring Boot and Eureka.

There are three services:

* A Eureka Server for service discovery
* An Order service that manages orders
* A Payment service that communicates with the Order service

Instead of calling the Order service using localhost, the Payment service uses the Eureka service name. Eureka resolves the service location dynamically. This approach enables loose coupling, scalability, and easier service management.
