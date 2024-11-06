import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core'; // Ajoutez ceci si vous avez besoin de désactiver les erreurs pour les composants non reconnus
import { CreateProjectComponent } from './creation-project.component';

describe('CreateProjectComponent Integration', () => {
  let component: CreateProjectComponent;
  let fixture: ComponentFixture<CreateProjectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,
        HttpClientTestingModule,
        CreateProjectComponent // Importez le composant ici
      ],
      schemas: [NO_ERRORS_SCHEMA], // Utilisez ceci si vous ne voulez pas gérer tous les composants enfants
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateProjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges(); // Détectez les changements
  });

  it('should handle error on project creation', async () => {
    // Logique du test pour la gestion d'erreur
  });

  it('should submit the project form successfully', async () => {
    // Logique du test pour la soumission réussie du formulaire
  });

  it('should initialize the component and load user email and ID', async () => {
    // Logique du test pour l'initialisation du composant
  });
});
