# Utiliser l'image officielle de OpenJDK
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier JAR de l'application dans le conteneur
COPY target/pmt-backend-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port 8080 sur lequel l'application écoute
EXPOSE 8080

# Commande pour lancer l'application Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
