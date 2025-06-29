# Base image with Java
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy jar file into the container
COPY target/*.jar app.jar

# Expose port 8081 instead of 8080
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
