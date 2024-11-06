import { Component } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  imports: [
    RouterModule,
    RouterOutlet,
    CommonModule,
  ],
})
export class AppComponent {
  someMethodThatNavigates() {
    throw new Error('Method not implemented.');
  }
  login() {
    throw new Error('Method not implemented.');
  }
  public title = 'PMT Tool';

  constructor(public authService: AuthService, public router: Router) {}

  logout(): void {
    this.authService.logout();
  }

  goToInscription(): void {
    this.router.navigate(['/inscription']);
  }

  goToConnexion(): void {
    this.router.navigate(['/connexion']);
  }

  goToCreateProject(): void {
    this.router.navigate(['/creation-projet']);
  }

  // Méthode pour naviguer vers le tableau de bord des tâches
  goToTaskDashboard(): void {
    this.router.navigate(['/task-dashboard']);
  }

  // Méthode pour vérifier si l'utilisateur est connecté
  isUserAuthenticated(): boolean {
    return this.authService.checkAuthStatus();
  }
}
