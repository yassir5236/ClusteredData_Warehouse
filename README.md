# Clustered Data Warehouse – FX Deals Importer


A Spring Boot-powered service for capturing and storing Foreign Exchange (FX) deal data within a scalable data warehouse.

## Key Features

- Receives FX deal information and saves it to a PostgreSQL database
- Performs validation on incoming deal data, including currency code verification
- Ensures idempotency by rejecting duplicate deals based on unique Deal IDs
- Implements a no-rollback policy to persist all valid entries regardless of partial errors
- Robust error handling coupled with detailed logging
- Comprehensive unit tests to maintain high code quality
- Containerized using Docker for easy deployment and scaling

## Technology Stack

- **Java 17** — Core language and runtime
- **Spring Boot 3.5.0** — Framework for application development
- **PostgreSQL** — Relational database management system
- **Maven** — Build and dependency management
- **Docker & Docker Compose** — Containerization and orchestration
- **Lombok** — Boilerplate code reduction
- **MapStruct** — DTO-to-entity mapping
- **JUnit 5** — Unit testing framework
- **SLF4J** — Logging facade
- **Jakarta Validation** — Bean validation API

## Setup Instructions

### Prerequisites

- Docker & Docker Compose installed
- Maven build tool
- Java 17 JDK
- Git CLI

### Cloning the Project

```bash
git clone https://github.com/yourusername/FxDealsWarehouse.git
cd FxDealsWarehouse
```

give also this in other way to just copy and past to me in one file


### Build and Run**
```bash
make up
```
This starts the application and PostgreSQL database using Docker Compose.

## Makefile Commands

- `make help`: Show available commands
- `make up`: Start Docker containers in detached mode
- `make down`: Stop and remove Docker containers
- `make test`: Run Maven tests
- `make clean`: Clean Maven project and remove target directory

## API Endpoints

### Insert Deal
- **Endpoint**: `POST /api/deals/import`
- **URL**: `http://localhost:8081/api/deals/import`
- **Request JSON Example**:
```json
{
  "dealId": "D0010",
  "fromCurrencyCode": "GBP",
  "toCurrencyCode": "EUR",
  "dealAmount": 1000.50
}
```
- **Response** (HTTP 201 Created):
```json
{
  "dealId": "D0010",
  "fromCurrencyCode": "GBP",
  "toCurrencyCode": "EUR",
  "dealTimestamp": "2025-06-03T10:16:22.7500753",
  "dealAmount": 1000.50
}
```

## Request Validation

- **Fields Validated**:
    - `dealId`: Must not be blank
    - `fromCurrencyCode`: Must be a valid 3-letter ISO code (e.g., "USD")
    - `toCurrencyCode`: Must be a valid 3-letter ISO code (e.g., "SGD")
    - `dealAmount`: Must be positive and not null
- **Validation Mechanism**:
    - Uses Jakarta Validation annotations (`@NotBlank`, `@NotNull`, `@Positive`)
    - Throws specific exceptions for invalid data (`InvalidCurrencyException`, `CurrencyNotAvailableException`)

## Database Interaction

- **Database**: PostgreSQL
- **Entity**: `Deal` table with columns:
    - `id` (String, Primary Key)
    - `fromCurrencyCode` (String, 3 chars)
    - `toCurrencyCode` (String, 3 chars)
    - `dealTimestamp` (LocalDateTime)
    - `dealAmount` (BigDecimal)
- **JPA**: Spring Data JPA with auditing for timestamp
- **Duplicate Prevention**: Unique constraint on `id` column

## Testing

- **Framework**: JUnit 5 
- **Coverage**:
    - Unit tests for service layer (`BaseDealServiceTest`)
    - Currency checker tests (`CurrencyCheckerImplTest`)
- **Location**: `src/test/java`
- **Run**: `make test`

## Dockerization

- **Dockerfile**: Multi-stage build
    - Maven stage: Builds the application
    - Runtime stage: Runs the JAR with Eclipse Temurin JDK 17
- **Docker Compose**:
    - `app`: Spring Boot application on port 8081
    - `db`: PostgreSQL on port 5433
    - Volumes for persistent data and Maven cache

## Project Structure

```
fx-deals-importer/
├── docker-compose.yml
├── Dockerfile
├── Makefile
├── pom.xml
├── readme.md
├── src/
│   ├── main/
│   │   ├── java/com/progressoft/fxdealsimporter/
│   │   │   ├── config/          # Configuration classes
│   │   │   ├── controller/      # REST controllers
│   │   │   ├── dto/            # Data Transfer Objects
│   │   │   ├── entity/         # JPA entities
│   │   │   ├── HandelException/ # Custom exceptions
│   │   │   ├── mapper/         # Object mappers
│   │   │   ├── repository/     # JPA repositories
│   │   │   └── service/        # Business logic
│   │   └── resources/          # Configuration files, currencies.csv
│   └── test/                   # Unit tests
└── target/                     # Build artifacts
```

## Error Handling

- Global exception handler returning structured `ErrorResponse`
- Logging with SLF4J for debugging and monitoring

