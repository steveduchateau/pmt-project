# Utiliser l'image officielle de OpenJDK
FROM openjdk:21-jdk-slim

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier JAR de l'application dans le conteneur
COPY target/pmt-backend-0.0.1-SNAPSHOT.jar app.jar

# Copier le fichier de configuration application-docker.properties dans le conteneur
COPY src/main/resources/application-docker.properties /app/application-docker.properties

# Exposer le port 8080 sur lequel l'application écoute
EXPOSE 8080

# Commande pour lancer l'application Spring Boot avec le fichier de configuration
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=file:/app/application-docker.properties"]
