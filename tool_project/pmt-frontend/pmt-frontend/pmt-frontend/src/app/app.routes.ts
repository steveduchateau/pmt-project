import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InscriptionComponent } from './inscription/inscription.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { CreateProjectComponent } from './creation-project/creation-project.component';
import { ProjectDetailsComponent } from './project-details/project-details.component';
import { TaskDashboardComponent } from './task-dashboard/task-dashboard.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';

export const routes: Routes = [
  { path: 'inscription', component: InscriptionComponent },
  { path: 'connexion', component: ConnexionComponent },
  { path: 'creation-projet', component: CreateProjectComponent },
  { path: 'project-details/:id', component: ProjectDetailsComponent },
  { path: 'task-dashboard', component: TaskDashboardComponent },
  { path: '', redirectTo: '/connexion', pathMatch: 'full' },
  { path: '**', redirectTo: '/connexion' }, // Gérer les routes non trouvées
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })], // Utiliser le mode hash
  exports: [RouterModule],
  providers: [{ provide: LocationStrategy, useClass: HashLocationStrategy }] // Fournir HashLocationStrategy
})
export class AppRoutingModule {}
