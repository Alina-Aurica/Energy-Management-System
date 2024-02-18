# Use an official Maven image as the build environment
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory within the container
WORKDIR /app

# Copy the Maven project files and source code to the container
COPY ./pom.xml ./pom.xml
COPY ./src ./src
COPY ./checkstyle.xml ./checkstyle.xml

# Build the Maven project (skip tests)
RUN mvn clean install -DskipTests

# Use an official OpenJDK image as the base image for Java Spring
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the compiled Java Spring application (JAR file) to the container
COPY target/DeviceManagementService-0.0.1-SNAPSHOT.jar .

# Expose the port your Java Spring application is running on
EXPOSE 8081

# Start the Devices Microservice
CMD ["java", "-jar", "DeviceManagementService-0.0.1-SNAPSHOT.jar"]