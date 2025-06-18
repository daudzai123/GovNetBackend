# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the working directory in the container
#WORKDIR /app

# Copy the application JAR file into the container at the specified working directory just for test
#COPY target/documentmis.jar /app
COPY target/documentmis.jar documentmis.jar

# Specify the command to run on container start
ENTRYPOINT  ["java", "-jar", "documentmis.jar"]
