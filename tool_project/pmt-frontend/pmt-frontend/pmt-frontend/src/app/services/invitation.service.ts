import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class InvitationService {
  private invitations: string[] = [];
  private apiUrl = 'http://localhost:8080/auth/projects'; // URL de base pour les invitations

  constructor(private http: HttpClient) { }

  addInvitation(invitation: string): void {
    if (invitation && !this.invitations.includes(invitation)) {
      this.invitations.push(invitation);
    }
  }

  getInvitations(): string[] {
    return this.invitations;
  }

  clearInvitations(): void {
    this.invitations = [];
  }

  inviteMember(projectId: string, memberData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/${projectId}/invite`, memberData).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    // Gérer les erreurs ici, en fonction du code d'erreur
    if (error.status === 400) {
      return throwError('Bad Request');
    } else if (error.status === 401) {
      return throwError('Unauthorized');
    } else if (error.status === 404) {
      return throwError('Not Found');
    } else if (error.status === 500) {
      return throwError('Server Error');
    }
    return throwError('Unknown Error');
  }
}
