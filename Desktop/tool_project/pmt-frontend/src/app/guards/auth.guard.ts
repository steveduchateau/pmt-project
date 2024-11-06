import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AutGuardService implements CanActivate {
  private isLoggedIn: boolean = false; // État de l'authentification
  private userRoles: string[] = []; // Rôles de l'utilisateur

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    // Si l'utilisateur n'est pas connecté, refusez l'accès
    if (!this.isLoggedIn) {
      return false; // Accès refusé si l'utilisateur n'est pas connecté
    }

    // Récupérer le rôle requis de la route
    const requiredRole = route.data?.['role'];
    if (requiredRole) {
      return this.hasRequiredRole(requiredRole); // Vérifiez si l'utilisateur a le rôle requis
    }

    return true; // Si aucun rôle requis, accès autorisé
  }

  private hasRequiredRole(role: string): boolean {
    // Vérifiez si l'utilisateur a le rôle requis
    return this.userRoles.includes(role);
  }

  // Méthode pour simuler l'authentification (juste pour les tests)
  setLoggedIn(value: boolean, roles: string[] = []): void {
    this.isLoggedIn = value;
    this.userRoles = roles; // Mettez à jour les rôles de l'utilisateur
  }
}
