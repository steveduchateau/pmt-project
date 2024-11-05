// src/app/app.config.ts
import { provideHttpClient } from '@angular/common/http';
import { provideRouter, Router } from '@angular/router';
import { provideAnimations } from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { withFetch } from '@angular/common/http'; // Importez withFetch
import { ConnexionComponent } from './connexion/connexion.component';
import { CreateProjectComponent } from './creation-project/creation-project.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { ProjectDetailsComponent } from './project-details/project-details.component';
import { TaskDashboardComponent } from './task-dashboard/task-dashboard.component';

export const appConfig = {
  providers: [
    provideHttpClient(withFetch()), // Configurez HttpClient pour utiliser fetch
    provideRouter([
      // Définir vos routes ici
      { path: '', redirectTo: '/connexion', pathMatch: 'full' }, // Redirection par défaut
      { path: 'inscription', component: InscriptionComponent },
      { path: 'connexion', component: ConnexionComponent },
      { path: 'creation-projet', component: CreateProjectComponent },
      { path: 'project-details/:id', component: ProjectDetailsComponent },
      { path: 'task-dashboard', component: TaskDashboardComponent },
      { path: '**', redirectTo: '/connexion' }, // Gérer les routes non trouvées
    ]),
    provideAnimations()
  ]
};
