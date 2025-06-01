# Use Eclipse Temurin OpenJDK 21 base image with JRE only (smaller)
FROM eclipse-temurin:21-jre-alpine

# Set working directory inside the container
WORKDIR /app

# Copy the JAR file built by Maven into the container
COPY target/*.jar app.jar

# Expose the port your Spring Boot app runs on (default 8080)
EXPOSE 8080

# Run the Spring Boot JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]