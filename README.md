
## CRUD Todo Application

A simple **CRUD Todo Application**

### Features

- **Create**: Add new todo items.
- **Read**: View all todo items, categorized by completed and incomplete.
- **Update**: Mark tasks as completed.
- **Delete**: Remove tasks from the list.

### Requirements

- **Java 21** 
- **Maven** (for building the project)
- **PostgreSQL** (can also run with Docker)
- **Docker** (optional, for containerization)
- **GraalVM** (optional, for native image build)

### Setup Instructions

#### 1. Clone the Repository

```bash
git clone https://github.com/h00jie/crudtodohtmx.git
cd crud-todo-htmx
```

#### 2. Database Setup

If you have **PostgreSQL** installed, configure the database in your `application.properties` or `application.yml`.

Example configuration:

```properties
# src/main/resources/application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/todo_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```


This will start **Spring Boot** application and **PostgreSQL** database.

#### 3. Build and Run the Application 

You can build and run the Spring Boot application using **Maven**:

```bash
./mvnw clean package
./mvnw spring-boot:run
```

The application will start on **http://localhost:8080**.

### Endpoints

- **`GET /todos`**: View the todo list.
- **`POST /todos`**: Add a new todo item (form submission).
- **`POST /todos/{id}/complete`**: Mark a todo item as complete.
- **`DELETE /todos/{id}`**: Delete a todo item.

### Running Tests

You can run the tests for the application with:

```bash
./mvnw test
```

### Technologies Used

- **Spring Boot**: Framework for building the backend and handling RESTful APIs.
- **HTMX**: For partial page updates and enhanced interactivity without needing to use JavaScript.
- **Thymeleaf**: For server-side rendering of HTML templates.
- **PostgreSQL**: For storing todo items.
- **Tailwind CSS**: For styling the front-end.
- **Docker**: For containerizing the application and database.


### Future Improvements

- Add user authentication for multi-user task management.
- Implement advanced task features like due dates and task priorities.
- Add pagination and filtering to the todo list.

### License

This project is licensed under the MIT License. See the `LICENSE` file for details.

---

### Closing Notes

You now have a simple yet functional CRUD Todo Application with Spring Boot, HTMX, PostgreSQL, and Tailwind CSS. Feel free to customize, extend, and improve the application as per your needs!