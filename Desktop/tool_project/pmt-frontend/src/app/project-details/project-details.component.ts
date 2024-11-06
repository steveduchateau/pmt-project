import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ProjectService } from '../services/project.service';

interface ProjectMember {
  id: number;
  email: string;
  role: string; // "admin", "membre", "observer"
  userId: number; // ID de l'utilisateur
  projectId: number; // ID du projet
}

interface Task {
  id: number;
  name: string;
  description: string;
  dueDate: string;
  priority: string;
  assignedTo: string;
  status: string;
  projectId: number | null;
}

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule
  ]
})
export class ProjectDetailsComponent implements OnInit {
cancelEdit() {
throw new Error('Method not implemented.');
}
  projectId: number | null = null;
  currentProjectId: number | null = null;
  newMemberEmail: string = '';
  newMemberRole: string = 'membre';
  projectMembers: ProjectMember[] = []; // Liste des membres du projet
  newTask: Task = {
    id: 0,
    name: '',
    description: '',
    dueDate: '',
    priority: 'medium',
    assignedTo: '',
    status: 'non démarré',
    projectId: null
  };
  tasks: Task[] = [];
  priorityOptions: string[] = ['low', 'medium', 'high'];
  statusOptions: string[] = ['Non démarrée', 'En cours', 'Terminée'];
  isObserver: boolean = false;
  isAdmin: boolean = false;
  projectCreatorEmail: string = '';
  projectCreatorId: number | null = null;
  taskToEdit: Task | null = null;
  currentUserEmail: string = '';

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar,
    private projectService: ProjectService
  ) {}

  ngOnInit(): void {
    this.projectId = Number(this.route.snapshot.paramMap.get('id'));
    this.currentProjectId = this.projectId;
    this.currentUserEmail = localStorage.getItem('userEmail') || '';
    
    if (this.projectId) {
      this.loadProjectDetails(); // Charge les détails du projet
    }
  }
  
  loadProjectDetails() {
    this.http.get(`http://localhost:8080/auth/projects/${this.projectId}`).subscribe({
      next: (response: any) => {
        this.projectCreatorEmail = response.creatorEmail;
        this.projectCreatorId = response.creatorId;
        const currentUserId = Number(localStorage.getItem('userId'));
  
        this.projectService.isUserAdmin(this.projectId!, currentUserId).subscribe({
          next: (isAdmin) => {
            this.isAdmin = isAdmin || this.projectCreatorEmail === this.currentUserEmail;
            this.loadProjectMembers(); // Charge les membres du projet
            this.loadProjectTasks(); // Charge les tâches du projet
          },
          error: (err) => {
            console.error('Erreur lors de la vérification du rôle de l\'utilisateur', err);
            this.snackBar.open('Erreur lors de la vérification du rôle de l\'utilisateur', 'Fermer', { duration: 3000 });
          }
        });
      },
      error: (err) => {
        console.error('Erreur lors du chargement des détails du projet', err);
        this.snackBar.open('Erreur lors du chargement des détails du projet', 'Fermer', { duration: 3000 });
      }
    });
  }
  
  loadProjectMembers() {
    this.http.get<ProjectMember[]>(`http://localhost:8080/auth/projects/${this.projectId}/members`).subscribe({
      next: (members) => {
        this.projectMembers = members; // Met à jour la liste des membres du projet
        this.checkUserRole(); // Vérifie le rôle de l'utilisateur
      },
      error: (err) => {
        console.error('Erreur lors du chargement des membres du projet', err);
        this.snackBar.open('Erreur lors du chargement des membres du projet', 'Fermer', { duration: 3000 });
      }
    });
  }
  
  checkUserRole() {
    const currentUserId = Number(localStorage.getItem('userId'));
    const currentUser = this.projectMembers.find(member => member.email === this.currentUserEmail);

    // Vérifie si l'utilisateur est le créateur du projet
    if (this.currentUserEmail === this.projectCreatorEmail) {
        this.isAdmin = true; // L'utilisateur est admin car il est le créateur
        this.isObserver = false; // Pas observateur
        console.log('L\'utilisateur est le créateur du projet, il est admin.');
    } else if (currentUserId === this.projectCreatorId) {
        this.isAdmin = true;
        this.isObserver = false; // L'utilisateur est admin, donc pas observateur
        console.log('L\'utilisateur est le créateur du projet par ID, il est admin.');
    } else if (currentUser) {
        // Si l'utilisateur est un membre, vérifie son rôle
        this.isAdmin = currentUser.role === 'admin';
        this.isObserver = currentUser.role === 'observer';
        console.log(`Rôle de l'utilisateur vérifié: ${currentUser.role}`); // Log du rôle vérifié
    } else {
        // Si l'utilisateur n'est pas un membre du projet, le considérer comme observateur
        this.isAdmin = false; // L'utilisateur n'est pas admin
        this.isObserver = true;
        console.log('L\'utilisateur n\'est pas membre du projet, il est considéré comme observateur.');
    }
  }

  inviteMember() {
    if (!this.isObserver) {
      const invitePayload = { email: this.newMemberEmail, role: this.newMemberRole, projectId: this.projectId };
      this.http.post(`http://localhost:8080/auth/projects/${this.projectId}/invite`, invitePayload).subscribe({
        next: () => {
          this.loadProjectMembers(); // Recharger les membres après invitation
          this.resetNewMemberFields();
          this.snackBar.open('Invitation envoyée avec succès', 'Fermer', { duration: 3000 });
        },
        error: (error) => {
          console.error('Erreur lors de l\'invitation:', error);
          this.snackBar.open('Erreur lors de l\'invitation: ' + (error.error?.message || 'Veuillez réessayer'), 'Fermer', { duration: 3000 });
        }
      });
    }
  }

  resetNewMemberFields() {
    this.newMemberEmail = '';
    this.newMemberRole = 'membre';
  }

  removeMember(member: ProjectMember) {
    if (this.isAdmin) {
      const projectId = this.currentProjectId;
      const memberId = member.id;

      this.http.delete(`http://localhost:8080/auth/projects/${projectId}/members/${memberId}`).subscribe({
        next: () => {
          this.loadProjectMembers(); // Recharger les membres après suppression
          this.snackBar.open('Membre supprimé avec succès', 'Fermer', { duration: 3000 });
        },
        error: (error) => {
          console.error('Erreur lors de la suppression du membre:', error);
          this.snackBar.open('Erreur lors de la suppression du membre', 'Fermer', { duration: 3000 });
        }
      });
    }
  }

  createTask() {
    if (!this.isObserver) {
      if (!this.newTask.name || !this.newTask.description || !this.newTask.dueDate || !this.newTask.assignedTo) {
        this.snackBar.open('Tous les champs doivent être remplis pour créer une tâche', 'Fermer', { duration: 3000 });
        return;
      }

      const currentTime = new Date().toISOString();

      const taskPayload = {
        ...this.newTask,
        projectId: this.projectId,
        assignedBy: this.currentUserEmail,
        createdAt: currentTime,
        updatedAt: currentTime
      };

      this.http.post(`http://localhost:8080/auth/tasks/${this.projectId}/tasks`, taskPayload).subscribe({
        next: () => {
          this.loadProjectTasks(); // Recharger les tâches après création
          this.resetNewTaskFields();
          this.snackBar.open('Tâche créée avec succès', 'Fermer', { duration: 3000 });
        },
        error: (error) => {
          console.error('Erreur lors de la création de la tâche:', error);
          this.snackBar.open('Erreur lors de la création de la tâche', 'Fermer', { duration: 3000 });
        }
      });
    }
  }

  resetNewTaskFields() {
    this.newTask = {
      id: 0,
      name: '',
      description: '',
      dueDate: '',
      priority: 'medium',
      assignedTo: '',
      status: 'non démarré',
      projectId: null
    };
  }

  editTask(task: Task) {
    this.taskToEdit = task;
  }

  updateTask() {
    if (!this.isObserver && this.taskToEdit) {
        const taskPayload = { ...this.taskToEdit };
        // Supposons que vous ayez accès à `projectId` dans votre composant
        const projectId = this.taskToEdit.projectId; // ou la manière dont vous récupérez l'ID du projet
        this.http.put(`http://localhost:8080/auth/tasks/${projectId}/tasks/${this.taskToEdit.id}`, taskPayload).subscribe({
            next: () => {
                this.loadProjectTasks(); // Recharger les tâches après mise à jour
                this.taskToEdit = null; // Réinitialiser l'édition
                this.snackBar.open('Tâche mise à jour avec succès', 'Fermer', { duration: 3000 });
            },
            error: (error) => {
                console.error('Erreur lors de la mise à jour de la tâche:', error);
                this.snackBar.open('Erreur lors de la mise à jour de la tâche', 'Fermer', { duration: 3000 });
            }
        });
    }
}


  loadProjectTasks() {
    this.http.get<Task[]>(`http://localhost:8080/auth/tasks/${this.projectId}/tasks`).subscribe({
      next: (tasks) => {
        this.tasks = tasks; // Met à jour la liste des tâches du projet
      },
      error: (err) => {
        console.error('Erreur lors du chargement des tâches du projet', err);
        this.snackBar.open('Erreur lors du chargement des tâches du projet', 'Fermer', { duration: 3000 });
      }
    });
  }

  deleteTask(taskId: number) {
    if (!this.isObserver) {
      this.http.delete(`http://localhost:8080/auth/tasks/${taskId}`).subscribe({
        next: () => {
          this.loadProjectTasks(); // Recharger les tâches après suppression
          this.snackBar.open('Tâche supprimée avec succès', 'Fermer', { duration: 3000 });
        },
        error: (error) => {
          console.error('Erreur lors de la suppression de la tâche:', error);
          this.snackBar.open('Erreur lors de la suppression de la tâche', 'Fermer', { duration: 3000 });
        }
      });
    }
  }
}
