import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { of, throwError } from 'rxjs';
import { ConnexionComponent } from './connexion.component'; // Assurez-vous que le chemin est correct
import { NoopAnimationsModule } from '@angular/platform-browser/animations';

describe('ConnexionComponent', () => {
  let component: ConnexionComponent;
  let fixture: ComponentFixture<ConnexionComponent>;
  let mockAuthService: jasmine.SpyObj<AuthService>;
  let mockRouter: jasmine.SpyObj<Router>;
  let mockSnackBar: jasmine.SpyObj<MatSnackBar>;

  beforeEach(async () => {
    // Création des mocks pour les services
    mockAuthService = jasmine.createSpyObj('AuthService', ['login', 'getAuthenticatedUserEmail', 'handleLogin']);
    mockRouter = jasmine.createSpyObj('Router', ['navigate']);
    mockSnackBar = jasmine.createSpyObj('MatSnackBar', ['open']);

    await TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,    // Importation du module des formulaires réactifs
        NoopAnimationsModule,    // Importation pour éviter les animations pendant les tests
        // ConnexionComponent ne doit pas être déclaré ici
      ],
      declarations: [
        // Ne pas inclure ConnexionComponent ici car il est autonome
      ],
      providers: [
        { provide: AuthService, useValue: mockAuthService }, // Utilisation de la version mock du service Auth
        { provide: Router, useValue: mockRouter },           // Utilisation de la version mock du service Router
        { provide: MatSnackBar, useValue: mockSnackBar }     // Utilisation de la version mock du service MatSnackBar
      ],
    }).compileComponents(); // Compilation des composants

    // Créer le composant avec le TestBed
    fixture = TestBed.createComponent(ConnexionComponent); // Création de l'instance du composant
    component = fixture.componentInstance; // Attribution de l'instance au composant
  });

  it('should create', () => {
    expect(component).toBeTruthy(); // Vérification que le composant a été créé
  });

  it('should navigate to creation-projet if user is already authenticated', () => {
    mockAuthService.getAuthenticatedUserEmail.and.returnValue(of('user@example.com')); // Simulation d'un utilisateur authentifié
    component.ngOnInit(); // Appel de ngOnInit pour vérifier la navigation
    expect(mockRouter.navigate).toHaveBeenCalledWith(['/creation-projet']); // Vérification de la navigation
  });

  it('should handle null email gracefully', () => {
    mockAuthService.getAuthenticatedUserEmail.and.returnValue(of(null)); // Simulation d'un email nul
    component.ngOnInit(); // Appel de ngOnInit pour vérifier le comportement
    expect(mockRouter.navigate).not.toHaveBeenCalled(); // Vérification qu'il n'y a pas eu de navigation
  });

  it('should show error message on login failure', () => {
    component.loginForm.setValue({ email: 'user@example.com', password: 'wrongpassword' }); // Configuration d'un formulaire de connexion
    mockAuthService.login.and.returnValue(throwError(() => new Error('Login failed'))); // Simulation d'une erreur de connexion

    component.onSubmit(); // Soumission du formulaire
    expect(component.errorMessage).toBe('Une erreur est survenue lors de la connexion. Veuillez réessayer.'); // Vérification du message d'erreur

    const messageToShow = component.errorMessage || 'Erreur inconnue'; // Définition d'un message par défaut
    expect(mockSnackBar.open).toHaveBeenCalledWith(messageToShow, 'Fermer', { duration: 3000, panelClass: ['snackbar-error'] }); // Vérification de l'affichage du snackBar
  });
});
