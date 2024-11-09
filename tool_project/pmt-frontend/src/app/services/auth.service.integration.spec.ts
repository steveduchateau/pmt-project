import { TestBed } from '@angular/core/testing';
import { AuthService } from './auth.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Router } from '@angular/router';
import { of } from 'rxjs';

describe('AuthService Integration Test', () => {
  let service: AuthService;
  let httpMock: HttpTestingController;
  let routerMock: { navigate: jasmine.Spy };

  beforeEach(() => {
    routerMock = {
      navigate: jasmine.createSpy('navigate')
    };

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        AuthService,
        { provide: Router, useValue: routerMock }
      ]
    });

    service = TestBed.inject(AuthService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Vérifie qu'il n'y a pas de requêtes HTTP en attente
  });

  it('should login and navigate to project creation on successful login', () => {
    const mockResponse = {
      message: 'Login successful',
      Id: 1,
      email: 'test@example.com',
      createdProjects: []
    };

    service.login('test@example.com', 'password123').subscribe(response => {
      service.handleLogin(response); // Simule la gestion de la connexion
    });

    const req = httpMock.expectOne('http://localhost:8080/auth/login');
    expect(req.request.method).toEqual('POST');
    req.flush(mockResponse); // Simule la réponse du serveur

    expect(service.checkAuthStatus()).toBeTrue(); // Vérifie que l'utilisateur est authentifié
    expect(routerMock.navigate).toHaveBeenCalledWith(['/creation-projet']); // Vérifie la redirection
  });

  it('should logout and navigate to login', () => {
    service.logout(); // Simule la déconnexion

    expect(service.checkAuthStatus()).toBeFalse(); // Vérifie que l'utilisateur n'est plus authentifié
    expect(routerMock.navigate).toHaveBeenCalledWith(['/connexion']); // Vérifie la redirection
  });
});
