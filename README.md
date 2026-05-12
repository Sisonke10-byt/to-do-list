# To-Do List Application

A Spring Boot REST API for managing your daily tasks and to-do items. This application provides a clean, simple interface to create, read, update, and delete to-do items with completion status tracking.

## Features

- ✅ Create new to-do items
- ✅ View all to-do items or filter by completion status
- ✅ Mark to-do items as completed
- ✅ Delete to-do items
- ✅ RESTful API endpoints
- ✅ PostgreSQL database integration
- ✅ Spring Data JPA for data persistence
- ✅ Comprehensive logging with SLF4J

## Technology Stack

- **Java 21** - Latest LTS version
- **Spring Boot 3.4.0** - Modern web framework with long-term support
- **Spring Data JPA** - Data access layer
- **PostgreSQL** - Relational database
- **Maven** - Build and dependency management
- **SLF4J** - Logging framework

## Prerequisites

- Java 21 (JDK 21+)
- Maven 3.6+
- PostgreSQL 12+ (for database)
- Git

## Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/YOUR_USERNAME/to-do-list.git
   cd to-do-list
   ```

2. **Set up PostgreSQL database**
   - If you want to run PostgreSQL locally, create a new database:
   ```sql
   CREATE DATABASE todo_db;
   ```
   - Update `application.properties` or `application.yml` with your database credentials
   - If you use the provided `docker-compose.yml`, the local Postgres service is exposed on host port `5433` and the app container connects to `todo-postgres:5432`

3. **Configure application properties**
   
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5433/todo_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.datasource.driver-class-name=org.postgresql.Driver
   
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=false
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```

## Building the Project

```bash
mvn clean install
```

This will compile the source code, run tests, and create a JAR file in the `target/` directory.

## Running the Application

### Using Docker Compose
```bash
docker compose up -d
```

This starts Postgres on host port `5433` and the app on `http://localhost:8080`.

To stop the services:
```bash
docker compose down
```

### Using Maven
```bash
./mvnw spring-boot:run
```

### Using the JAR file
```bash
java -jar target/to-do-list-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080` by default.

## API Endpoints

### Get all to-do items
```
GET /api/todos
GET /api/todos?completed=true   # Filter by completion status
```

### Create a new to-do item
```
POST /api/todos
Content-Type: application/json

{
  "title": "Buy groceries",
  "description": "Milk, bread, eggs",
  "completed": false
}
```

### Mark a to-do as completed
```
PUT /api/todos/{id}/complete
```

### Delete a to-do item
```
DELETE /api/todos/{id}
```

## Project Structure

```
to-do-list/
├── src/
│   ├── main/
│   │   ├── java/com/example/to_do_list/
│   │   │   ├── ToDoListApplication.java      # Main Spring Boot application
│   │   │   ├── controller/
│   │   │   │   └── TodoController.java       # REST API endpoints
│   │   │   ├── service/
│   │   │   │   └── TodoService.java          # Business logic
│   │   │   ├── repository/
│   │   │   │   └── TodoRepository.java       # Data access layer
│   │   │   └── model/
│   │   │       └── Todo.java                 # Entity class
│   │   └── resources/
│   │       ├── application.properties        # Configuration
│   │       └── application.yml               # YAML configuration
│   └── test/
│       └── java/com/example/to_do_list/
│           └── ToDoListApplicationTests.java # Integration tests
├── pom.xml                                   # Maven configuration
├── mvnw & mvnw.cmd                           # Maven wrapper scripts
└── README.md                                 # This file
```

## Development

### Building with Maven Wrapper (no Maven installation needed)
```bash
./mvnw clean install  # On Linux/Mac
mvnw.cmd clean install  # On Windows
```

### Running tests
```bash
mvn test
```

### View application logs
Logs are configured via SLF4J and will be output to the console by default. Adjust logging levels in `application.properties`:
```properties
logging.level.com.example.to_do_list=DEBUG
```

## Database Schema

The application automatically creates the `todo` table with the following structure:

```sql
CREATE TABLE todo (
  id BIGSERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  description TEXT,
  completed BOOLEAN DEFAULT FALSE,
  created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For issues or questions, please open an issue on GitHub.

---

**Last Updated:** February 5, 2026  
**Version:** 0.0.1-SNAPSHOT
