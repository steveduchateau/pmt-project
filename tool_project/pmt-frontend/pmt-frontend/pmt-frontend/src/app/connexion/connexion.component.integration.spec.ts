import { TestBed, ComponentFixture } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ConnexionComponent } from './connexion.component';
import { AuthService } from '../services/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { of } from 'rxjs';

describe('ConnexionComponent Integration', () => {
  let component: ConnexionComponent;
  let fixture: ComponentFixture<ConnexionComponent>;
  let authService: AuthService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule,
        ConnexionComponent // Importez le composant autonome ici
      ],
      providers: [AuthService, MatSnackBar]
    }).compileComponents();

    fixture = TestBed.createComponent(ConnexionComponent);
    component = fixture.componentInstance;
    authService = TestBed.inject(AuthService);
  });

  it('should successfully login and navigate', () => {
    // Simulez la méthode de connexion
    spyOn(authService, 'login').and.returnValue(of({ message: 'Login successful' }));

    component.loginForm.setValue({ email: 'test@example.com', password: 'password' });
    component.onSubmit();

    // Ici vous vérifieriez la navigation, les messages, etc. selon les interactions réelles
    // Exemple d'assertions à ajouter :
    // expect(authService.login).toHaveBeenCalledWith('test@example.com', 'password');
    // expect(authService.handleLogin).toHaveBeenCalled();
    // expect(mockRouter.navigate).toHaveBeenCalledWith(['/creation-projet']);
    // expect(component.successMessage).toBe('Connexion réussie !');
  });

  // D'autres tests pourraient être ajoutés pour vérifier les intégrations réelles
});
