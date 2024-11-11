# Project Management Tool (PMT)

## Description
PMT (Project Management Tool) est une plateforme de gestion de projet collaboratif destinée aux équipes de développement logiciel. Elle permet aux utilisateurs de planifier, suivre et collaborer sur des projets de manière efficace. Cette application permet de gérer les projets, les utilisateurs, les rôles, les tâches, et bien plus encore.

## Technologies utilisées
- **Frontend** : Angular
- **Backend** : Java avec Spring Boot
- **Base de données** : MySQL
- **Contrôle de version** : Git
- **IDE** : Vs Code

## Fonctionnalités principales
- **Gestion des utilisateurs** : Inscription, connexion et gestion des rôles.
- **Gestion des projets** : Création de projets, invitation de membres et gestion des rôles des utilisateurs dans les projets.
- **Gestion des tâches** : Création, attribution, mise à jour et suivi des tâches.
- **Notifications par e-mail** : Envoi de notifications lorsque des tâches sont assignées.
- **Tableau de bord** : Vue d'ensemble des tâches par statut et historique des modifications.

## Architecture
L'architecture de l'application se divise en deux parties principales :
- **Frontend** : Application Angular permettant l'interaction avec les utilisateurs.
- **Backend** : API REST développée avec Spring Boot pour gérer la logique métier, les données, et les utilisateurs.

### Schéma de la base de données
- **Utilisateurs** : Stocke les informations des utilisateurs (nom, e-mail, mot de passe).
- **Projets** : Stocke les informations des projets (nom, description, date de début).
- **Tâches** : Stocke les tâches (nom, description, date d'échéance, priorité, statut).
- **Rôles** : Gère les rôles des utilisateurs dans chaque projet (administrateur, membre, observateur).

## Installation et démarrage

### Prérequis
- **Node.js** (pour Angular)
- **JDK 17** ou supérieur (pour Spring Boot)
- **MySQL** (en fonction du choix de la base de données)

### Cloner le repository
Clonez le repository depuis GitHub :
```bash
git clone https://github.com/steveduchateau/pmt-project/tree/develop2

Backend (Spring Boot)
Allez dans le répertoire du backend2 :
cd pmt/backend2
MVN clean install 
mvn test 
mvn spring-boot:run


Pour le frontend 
cd pmt-frontend 
NPM install
ng build 

une fois fait revenir sur le projet global et lancer docker docker-compose up --build

L'application sera disponible sur http://localhost:4200.

Dockerisation

Backend Dockerfile
Le Dockerfile pour le backend est disponible dans le répertoire backend :

# Dockerfile pour le backend
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/backend.jar /app/backend.jar
CMD ["java", "-jar", "backend.jar"]
Frontend Dockerfile

Le Dockerfile pour le frontend est disponible dans le répertoire frontend :

# Dockerfile pour le frontend
FROM node:16
WORKDIR /app
COPY . /app
RUN npm install
CMD ["npm", "start"]
Docker Compose
Vous pouvez utiliser docker-compose pour démarrer à la fois le backend, le frontend et la base de données :

docker-compose up --build
Déploiement sur Docker Hub
Construisez et poussez les images Docker sur Docker Hub :
docker build -t steveduchateau/pmt-backend:latest ./backend
docker build -t steveduchateau/pmt-frontend:latest ./frontend
docker push steveduchateau/pmt-backend:latest
docker push steveduchateau/pmt-frontend:latest
CI/CD avec GitHub Actions
Une pipeline CI/CD a été mise en place pour automatiser les tests et le déploiement. La configuration se trouve dans le fichier .github/workflows/ci.yml.

Tests

j'ai intégré des tests unitaires et d'intégration dans l'application :

Tests du frontend : Utilisation de Jasmine pour tester les composants et services Angular.
Tests du backend : Utilisation de JUnit et Mockito pour tester les services Spring Boot.
Couverture des tests
Les rapports de couverture sont générés pour le frontend, avec une couverture minimale de 60% pour les instructions et les branches.

Conclusion

PMT est un outil complet pour la gestion de projets, offrant des fonctionnalités  pour le suivi des tâches et la gestion des utilisateurs. Grâce à l'utilisation de technologies modernes comme Angular, Spring Boot et Docker, l'application est prête à être déployée dans un environnement de production.

Auteurs

Steve DUCHATEAU
