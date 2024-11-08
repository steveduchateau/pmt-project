# Utilisation de l'image de base openjdk 17
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail dans l'image Docker
WORKDIR /app

# Copier le fichier pom.xml et le wrapper Maven
COPY ./Desktop/tool_project/pmt-backend2/pom.xml /app/

# Télécharger les dépendances Maven (sans construire le projet complet)
RUN ./mvnw dependency:go-offline

# Copier tout le code source dans l'image Docker
COPY ./Desktop/tool_project/pmt-backend2 /app

# Exposer le port de l'application backend
EXPOSE 8080

# Construire le projet avec Maven (clean install)
RUN ./mvnw clean install

# Démarrer l'application backend avec Spring Boot
CMD ["./mvnw", "spring-boot:run"]
