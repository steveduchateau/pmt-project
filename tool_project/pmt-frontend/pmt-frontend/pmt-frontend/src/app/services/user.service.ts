// src/app/services/user.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  // Correction de l'URL pour l'enregistrement d'utilisateur
  public apiUrl = 'http://localhost:8080/auth'; // API pour l'enregistrement

  constructor(private http: HttpClient) {}

  // Méthode pour la connexion
  login(user: { email: string; password: string }): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, user).pipe(
      catchError(this.handleError)
    );
  }

  // Méthode pour l'enregistrement d'un utilisateur
  registerUser(userData: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/register`, userData).pipe( // Utilisation de l'URL correcte
      catchError(this.handleError)
    );
  }

  // Méthode pour gérer les erreurs
  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An unknown error occurred!';
    if (error.error instanceof ErrorEvent) {
      // Erreur côté client
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Erreur côté serveur
      errorMessage = error.error.message // Utilise le message du serveur si disponible
        ? `Error Code: ${error.status}\nMessage: ${error.error.message}` 
        : `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(errorMessage);
  }
}
