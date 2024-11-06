# Étape 1 : Construire l'application Angular
FROM node:18 AS build

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier les fichiers package.json et package-lock.json dans le conteneur
COPY package*.json ./

# Installer les dépendances du projet
RUN npm install

# Copier tout le code du projet dans le conteneur
COPY . .

# Construire l'application pour la production
RUN npm run build -- --output-path=dist/pmt-frontend

# Étape 2 : Servir l'application avec NGINX
FROM nginx:alpine

# Copier les fichiers construits du répertoire dist/portfolio/browser vers le répertoire par défaut de NGINX
COPY --from=build /app/dist/pmt-frontend/browser /usr/share/nginx/html

# Copier le fichier de configuration NGINX personnalisé
COPY nginx.conf /etc/nginx/nginx.conf

# Exposer le port 81
EXPOSE 81

# Démarrer NGINX
CMD ["nginx", "-g", "daemon off;"]