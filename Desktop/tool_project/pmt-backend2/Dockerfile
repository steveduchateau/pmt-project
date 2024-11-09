# Utilisation de l'image de base openjdk 17
FROM openjdk:17-jdk-slim

# Installer Maven à partir des sources officielles
RUN apt-get update && apt-get install -y wget
RUN wget https://archive.apache.org/dist/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz
RUN tar xzvf apache-maven-3.8.6-bin.tar.gz -C /opt
RUN ln -s /opt/apache-maven-3.8.6/bin/mvn /usr/local/bin/mvn

# Vérification de la version de Maven et de Java
RUN mvn -version
RUN java -version

# Définir le répertoire de travail
WORKDIR /app

# Copier tout le contenu du répertoire actuel (contenant pmt-backend2) dans l'image Docker
COPY . /app

# Exposer le port de l'application backend
EXPOSE 8080

# Construire le projet avec Maven (en utilisant directement Maven)
RUN mvn clean install

# Démarrer l'application Spring Boot
CMD ["java", "-jar", "target/app.jar"]
