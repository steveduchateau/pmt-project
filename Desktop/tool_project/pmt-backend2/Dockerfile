# Utilisation de l'image de base openjdk 17
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le projet backend dans l'image Docker
COPY ./Desktop/tool_project/pmt-backend2 /app

# Exposer le port de l'application backend
EXPOSE 8080

# Exécuter Maven pour installer les dépendances
RUN ./mvnw clean install

# Démarrer l'application backend
CMD ["./mvnw", "spring-boot:run"]


# Commande pour lancer l'application Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
