import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject, of, catchError, throwError } from 'rxjs';
import { Router } from '@angular/router';

interface LoginResponse {
  message: string;
  Id?: number;
  email?: string;
  createdProjects?: any[];
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private isAuthenticated = false;
  private currentUserId: number | null = null;
  private currentUserEmailSubject = new BehaviorSubject<string | null>(null); // Utilisation d'un BehaviorSubject

  constructor(private http: HttpClient, private router: Router) {
    this.loadUserFromLocalStorage();
  }

  private isBrowser(): boolean {
    return typeof window !== 'undefined' && typeof localStorage !== 'undefined';
  }

  public loadUserFromLocalStorage(): void {
    if (this.isBrowser()) {
      const storedUserId = localStorage.getItem('userId');
      const storedUserEmail = localStorage.getItem('userEmail');

      if (storedUserId && storedUserEmail) {
        this.currentUserId = +storedUserId;
        this.currentUserEmailSubject.next(storedUserEmail); // Émettre l'email chargé
        this.isAuthenticated = true;
      }
    }
  }
  login(email: string, password: string): Observable<LoginResponse> {
    const body = { email, password };
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    
    return this.http.post<LoginResponse>('http://localhost:8080/auth/login', body, { headers })
      .pipe(
        catchError((error: HttpErrorResponse) => {
          // Gérer l'erreur ici (si nécessaire)
          return throwError(() => new Error('Login failed'));
        })
      );
  }
  

  handleLogin(response: LoginResponse): void {
    if (response.message === 'Login successful') {
      this.isAuthenticated = true;
      this.currentUserId = response.Id || null;
      const userEmail = response.email || null;
      this.currentUserEmailSubject.next(userEmail); // Émettre l'email lors de la connexion

      if (userEmail && this.isBrowser()) {
        localStorage.setItem('userEmail', userEmail);
      }

      if (this.currentUserId !== null && this.isBrowser()) {
        localStorage.setItem('userId', this.currentUserId.toString());
      }

      this.router.navigate(['/creation-projet']);
    }
  }

  logout(): void {
    this.isAuthenticated = false;
    this.currentUserId = null;
    this.currentUserEmailSubject.next(null); // Émettre null lors de la déconnexion

    if (this.isBrowser()) {
      localStorage.removeItem('userEmail');
      localStorage.removeItem('userId');
    }

    this.router.navigate(['/connexion']);
  }

  checkAuthStatus(): boolean {
    return this.isAuthenticated;
  }

  getAuthenticatedUserId(): Observable<number | null> {
    return of(this.currentUserId); // Renvoie un observable contenant currentUserId
  }

  getAuthenticatedUserEmail(): Observable<string | null> {
    return this.currentUserEmailSubject.asObservable(); // Retourne l'observable
  }
}
