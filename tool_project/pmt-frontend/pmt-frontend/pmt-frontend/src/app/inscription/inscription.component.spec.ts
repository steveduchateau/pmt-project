import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing'; // Importer le module de test HTTP
import { InscriptionComponent } from './inscription.component';
import { ReactiveFormsModule } from '@angular/forms'; // Importer ReactiveFormsModule pour utiliser les formulaires réactifs
import { Router } from '@angular/router';

describe('InscriptionComponent', () => {
  let component: InscriptionComponent;
  let fixture: ComponentFixture<InscriptionComponent>;
  let httpTestingController: HttpTestingController;
  let mockRouter: jasmine.SpyObj<Router>; // Utiliser un spy pour Router

  beforeEach(async () => {
    mockRouter = jasmine.createSpyObj('Router', ['navigate']); // Créer un mock pour Router

    await TestBed.configureTestingModule({
      imports: [
        InscriptionComponent,
        HttpClientTestingModule,
        ReactiveFormsModule // Ajouter ReactiveFormsModule ici
      ],
      providers: [
        { provide: Router, useValue: mockRouter } // Fournir le mock Router
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(InscriptionComponent);
    component = fixture.componentInstance;
    httpTestingController = TestBed.inject(HttpTestingController); // Injecter HttpTestingController
    fixture.detectChanges();
  });

  afterEach(() => {
    httpTestingController.verify(); // Vérifier qu'il n'y a pas de requêtes en attente
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize the form correctly', () => {
    expect(component.registerForm).toBeDefined();
    expect(component.registerForm.valid).toBeFalsy();
  });

  it('should require username, email, and password', () => {
    const usernameControl = component.registerForm.get('username');
    const emailControl = component.registerForm.get('email');
    const passwordControl = component.registerForm.get('password');

    usernameControl?.setValue('');
    emailControl?.setValue('');
    passwordControl?.setValue('');

    expect(component.registerForm.valid).toBeFalsy();
    expect(usernameControl?.valid).toBeFalsy();
    expect(emailControl?.valid).toBeFalsy();
    expect(passwordControl?.valid).toBeFalsy();
  });

  it('should submit the form and navigate on success', (done) => {
    const mockResponse = { message: 'Inscription réussie' };
    component.registerForm.setValue({
      username: 'testuser',
      email: 'test@example.com',
      password: 'password123'
    });

    component.onSubmit();

    const req = httpTestingController.expectOne('http://localhost:8080/auth/register');
    expect(req.request.method).toEqual('POST');
    req.flush(mockResponse); // Simuler la réponse du serveur

    expect(component.successMessage).toEqual(mockResponse.message);
    expect(component.errorMessage).toBeNull();
    
    // Attendre la navigation après le délai
    setTimeout(() => {
      expect(mockRouter.navigate).toHaveBeenCalledWith(['/connexion']); // Vérifier la navigation
      done(); // Indiquer que le test est terminé
    }, 1500);
  });

  it('should handle error response on form submission', () => {
    const mockErrorResponse = { error: { message: 'Erreur lors de l\'inscription. Veuillez réessayer.' } }; // Le message d'erreur complet
    component.registerForm.setValue({
      username: 'testuser',
      email: 'test@example.com',
      password: 'password123'
    });

    component.onSubmit();

    const req = httpTestingController.expectOne('http://localhost:8080/auth/register');
    expect(req.request.method).toEqual('POST');
    req.flush(mockErrorResponse, { status: 400, statusText: 'Bad Request' }); // Simuler une erreur

    expect(component.errorMessage).toEqual('Erreur lors de l\'inscription. Veuillez réessayer.'); // Vérification du message d'erreur complet
    expect(component.successMessage).toBeNull();
  });
});
