# Utilisation de l'image de base openjdk 17
FROM openjdk:17-jdk-slim

# Installer Maven dans l'image Docker
RUN apt-get update && apt-get install -y maven

# Définir le répertoire de travail
WORKDIR /app

# Copier le projet backend dans l'image Docker
COPY ./Desktop/tool_project/pmt-backend2 /app

# Exposer le port de l'application backend
EXPOSE 8080

# Construire le projet avec Maven (en utilisant directement Maven)
RUN mvn clean install

# Démarrer l'application Spring Boot
CMD ["java", "-jar", "target/app.jar"]
