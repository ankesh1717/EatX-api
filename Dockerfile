# Use official Java 17 JDK image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy everything from project to /app
COPY . .

# Give permission to mvnw script
RUN chmod +x ./mvnw

# Build the Spring Boot project
RUN ./mvnw clean package -DskipTests

# Run the jar file
CMD ["java", "-jar", "target/foodiesapi-0.0.1-SNAPSHOT.jar"]
