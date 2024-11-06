import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { AuthService } from '../services/auth.service'; // Importer le service d'authentification

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  standalone: true,
  imports: [CommonModule, RouterModule]
})
export class HeaderComponent {
  constructor(private authService: AuthService, private router: Router) {}

  isAuthenticated(): boolean {
    return this.authService.checkAuthStatus(); // Vérifier l'état d'authentification
  }

  logout(): void {
    this.authService.logout(); // Appel de la méthode logout du service
    this.router.navigate(['/connexion']); // Rediriger vers la page de connexion
  }
}
