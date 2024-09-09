# Use the official image as a parent image
FROM amazoncorretto

# Set the working directory
WORKDIR /app

# Copy the JAR file from target to the container
COPY target/todo-app.jar /app/todo-app.jar

# Expose the port Spring Boot will run on
EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "todo-app.jar"]
