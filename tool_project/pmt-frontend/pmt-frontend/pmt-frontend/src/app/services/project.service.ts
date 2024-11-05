import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Project } from '../models/project';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  static getProjects: any;
  getCurrentUser() {
    throw new Error('Method not implemented.');
  }
  isUserObserver(arg0: number, currentUserId: number) {
    throw new Error('Method not implemented.');
  }
  public apiUrl = 'http://localhost:8080/auth/projects'; // URL du backend Spring Boot

  constructor(private http: HttpClient) {}

  createProject(project: Project, currentUserId: number): Observable<Project> {
    const userId = localStorage.getItem('userId'); // Récupérer l'ID de l'utilisateur depuis le localStorage
    const headers = new HttpHeaders({
      'User-ID': userId || '', // Ajout de l'en-tête User-ID
      'Content-Type': 'application/json' // Indique que vous envoyez du JSON
    });

    return this.http.post<Project>(this.apiUrl, project, { headers });
  }

  getProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(this.apiUrl);
  }

  getProject(id: number): Observable<Project> {
    return this.http.get<Project>(`${this.apiUrl}/${id}`);
  }

  isUserAdmin(projectId: number, userId: number): Observable<boolean> {
    return this.http.get<boolean>(`${this.apiUrl}/${projectId}/isAdmin/${userId}`);
  }

  // Nouvelle méthode pour inviter un membre au projet
  inviteMember(projectId: number, email: string, role: string): Observable<any> {
    const body = { email, role };
    return this.http.post(`${this.apiUrl}/${projectId}/invite`, body, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }

  // Nouvelle méthode pour assigner une tâche à un membre
  assignTask(taskId: number, assignedTo: string): Observable<any> {
    const body = { assignedTo };
    return this.http.post(`${this.apiUrl}/tasks/${taskId}/assign`, body, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }

  // Nouvelle méthode pour supprimer un membre d'un projet
  deleteProjectMember(projectId: number, memberId: number): Observable<void> {
    const url = `${this.apiUrl}/${projectId}/members/${memberId}`;
    return this.http.delete<void>(url, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }
}
