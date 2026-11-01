# Campus Connect Backend

**Project name:** Campus Connect (campus-connect)
**Student:** Iradukunda Arsene
**Student ID:** 27206

Backend API for a campus marketplace where students order services from vendors on campus. Built with Spring Boot, Spring Data JPA and PostgreSQL.

## Tech stack
- Java 21
- Spring Boot (Spring Web, Spring Data JPA)
- PostgreSQL
- Maven
- Base package: `com.example.campus_connect`

## Entities
`Student`, `Vendor`, `ServiceType`, `Kiosk`, `ServiceOrder`, `ServiceReview`, and the enum `OrderStatus` (REQUESTED, ACCEPTED, DELIVERED, DECLINED).

## How to run
1. Install Java 21, Maven and PostgreSQL.
2. In PostgreSQL create an empty database:
   ```sql
   CREATE DATABASE campus_connect;
   ```
3. Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/campus_connect
   spring.datasource.username=postgres
   spring.datasource.password=YOUR_PASSWORD
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```
4. Start the API:
   ```
   mvn spring-boot:run
   ```
   The first start can take a minute. The API runs on `http://127.0.0.1:8080`.
5. Tables are created automatically by Hibernate. Enter the seed data through the save endpoints (service types, kiosks, vendors, students, service orders) in that order.

## Endpoints
| ID | Method and path |
|---|---|
| A2 | `POST /api/{path}/save`, `GET /api/{path}/all`, `GET /api/{path}/{id}`, `PUT /api/{path}/update/{id}`, `DELETE /api/{path}/delete/{id}` where `{path}` is `service-types`, `kiosks`, `vendors`, `students`, `service-orders` |
| B1 | `GET /api/students/by-lastname?lastName=` |
| B2 | `GET /api/service-orders/by-status?status=` |
| B3 | `GET /api/service-orders/between?start=&end=` |
| C1 | `GET /api/vendors/by-service-type?name=` |
| C2 | `GET /api/kiosks/free` |
| C3 | `GET /api/service-types/unused` |
| C4 | `GET /api/students/of-vendor/{vendorId}` |
| D1 | `POST /api/service-orders/save` |
| D2 | `PATCH /api/service-orders/{id}/status?status=` |
| D3 | `PUT /api/vendors/{vendorId}/service-types/{serviceTypeId}` |
| D4 | `PATCH /api/vendors/{vendorId}/kiosk?kioskNumber=` |
| E1 | `POST /api/service-reviews/save?serviceOrderId=` |
| E2 | `GET /api/service-reviews/student/{studentNumber}` |
| E3 | `DELETE /api/service-reviews/delete/{id}` and `DELETE /api/service-orders/delete/{id}` |
| F1 | `GET /api/service-orders/stats/by-status` |
| F2 | `PATCH /api/service-orders/cancel-day?vendorId=&date=` |

## Postman
Import `campus-connect-midterm.postman_collection.json` (File, Import). Each requirement has its own folder named by its ID, containing a success request and at least one failing case where the specification has one. The ids used by the requests are collection variables (Collection, Variables), so update them there if your database ids differ.
