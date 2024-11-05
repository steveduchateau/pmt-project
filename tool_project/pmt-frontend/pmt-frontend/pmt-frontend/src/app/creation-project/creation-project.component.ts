import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { ProjectService } from '../services/project.service';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-create-project',
  standalone: true,
  templateUrl: './creation-project.component.html',
  styleUrls: ['./creation-project.component.scss'],
  imports: [CommonModule, ReactiveFormsModule],
})
export class CreateProjectComponent implements OnInit, OnDestroy {
  errorMessage(errorMessage: any) {
      throw new Error('Method not implemented.');
  }
  projectForm: FormGroup;
  projects: any[] = [];
  currentUserEmail: string | null = null;
  currentUserId: number | null = null;
  isLoading: boolean = false;
  public subscription: Subscription = new Subscription();

  constructor(
    private fb: FormBuilder,
    private projectService: ProjectService,
    private router: Router,
    private authService: AuthService
  ) {
    this.projectForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      startDate: ['', [Validators.required, this.validDateValidator]],
    });
  }

  ngOnInit(): void {
    this.isLoading = true; // Démarrer le chargement

    if (!this.authService.checkAuthStatus()) {
      console.error('L\'utilisateur n\'est pas authentifié.');
      this.router.navigate(['/connexion']);
      return;
    }

    // Récupérer l'email de l'utilisateur connecté
    this.authService.getAuthenticatedUserEmail().subscribe(email => {
      this.currentUserEmail = email;
      console.log('Current User Email on Init:', this.currentUserEmail);

      if (this.currentUserEmail) {
        this.loadUserId(); // Charger l'ID utilisateur uniquement après avoir obtenu l'email
      } else {
        console.error('Aucun email utilisateur trouvé. Vérifiez la connexion.');
        this.isLoading = false; // Arrêter le chargement
        this.router.navigate(['/connexion']);
      }
    });
  }

  loadUserId(): void {
    this.authService.getAuthenticatedUserId().subscribe(id => {
      this.currentUserId = id;
      console.log('Current User ID on Init:', this.currentUserId);

      if (this.currentUserId === null) {
        console.error('Impossible de récupérer l\'ID de l\'utilisateur. Vérifiez la connexion.');
        alert('Votre session a expiré. Veuillez vous reconnecter.');
        this.router.navigate(['/connexion']);
      }

      this.isLoading = false; // Arrêter le chargement après avoir récupéré l'ID
      this.loadProjects(); // Charger les projets après avoir obtenu l'ID utilisateur
    });
  }

  validDateValidator(control: AbstractControl): { [key: string]: any } | null {
    return isNaN(Date.parse(control.value)) ? { 'invalidDate': true } : null;
  }

  onSubmit(): void {
    if (this.currentUserId === null || isNaN(this.currentUserId)) {
      console.error('ID de l\'utilisateur manquant ou invalide. Impossible de soumettre le formulaire.');
      alert('Votre session a expiré. Veuillez vous reconnecter.');
      this.router.navigate(['/connexion']);
      return;
    }

    if (this.projectForm.valid) {
      this.isLoading = true; 
      const newProject = this.projectForm.value;

      // Ajout des informations sur le créateur du projet
      newProject.creatorEmail = this.currentUserEmail; 
      newProject.creatorUserId = this.currentUserId; 
      newProject.members = [{ email: this.currentUserEmail, role: 'admin' }]; 
      
      console.log('Formulaire soumis avec les données:', newProject);

      this.projectService.createProject(newProject, this.currentUserId).subscribe({
        next: () => {
          this.loadProjects();
          this.projectForm.reset();
          this.isLoading = false; 
        },
        error: (error: any) => {
          console.error('Erreur lors de la création du projet:', error);
          alert('Une erreur est survenue lors de la création du projet.');
          this.isLoading = false; 
        }
      });
    } else {
      console.warn('Le formulaire est invalide. Veuillez vérifier les champs.');
    }
  }

  loadProjects(): void {
    this.isLoading = true; 
    this.projectService.getProjects().subscribe({
      next: (projects: any[]) => {
        this.projects = projects;
        this.isLoading = false; 
      },
      error: () => {
        console.error('Erreur lors du chargement des projets');
        this.isLoading = false; 
      }
    });
  }

  viewProjectDetails(projectId: number): void {
    this.router.navigate([`/project-details/${projectId}`]);
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe(); // Libérer les abonnements pour éviter les fuites de mémoire
  }
}
