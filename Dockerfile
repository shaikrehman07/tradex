# === Stage 1: Build with Maven ===
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom and src to image
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# === Stage 2: Run the Spring Boot app ===
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy JAR from previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot's default port
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
