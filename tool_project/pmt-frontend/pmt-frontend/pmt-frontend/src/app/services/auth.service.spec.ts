import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { of } from 'rxjs';

describe('AuthService', () => {
  let service: AuthService;
  let httpMock: HttpTestingController;
  let routerSpy: jasmine.SpyObj<Router>;

  beforeEach(() => {
    const spy = jasmine.createSpyObj('Router', ['navigate']);

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        AuthService,
        { provide: Router, useValue: spy }
      ]
    });

    service = TestBed.inject(AuthService);
    httpMock = TestBed.inject(HttpTestingController);
    routerSpy = TestBed.inject(Router) as jasmine.SpyObj<Router>;
  });

  afterEach(() => {
    httpMock.verify(); // Vérifier qu'aucune requête HTTP n'est en attente
    localStorage.clear(); // Nettoyer le localStorage après chaque test
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should keep localStorage empty if login fails', () => {
    const mockErrorResponse = { message: 'Login failed' };

    service.login('test@example.com', 'wrongpassword').subscribe(
      () => fail('Expected an error, but got a response'),
      error => {
        expect(error.message).toEqual(mockErrorResponse.message);
      }
    );

    const req = httpMock.expectOne('http://localhost:8080/auth/login');
    req.flush(mockErrorResponse, { status: 401, statusText: 'Unauthorized' });

    // Vérifiez que localStorage reste vide si la connexion échoue
    expect(localStorage.getItem('userEmail')).toBeNull();
    expect(localStorage.getItem('userId')).toBeNull();
  });

  it('should not navigate if login fails', () => {
    const mockErrorResponse = { message: 'Login failed' };
    
    service.login('test@example.com', 'wrongpassword').subscribe(
      () => fail('Expected an error, but got a response'),
      error => {
        expect(error.message).toEqual(mockErrorResponse.message);
      }
    );

    const req = httpMock.expectOne('http://localhost:8080/auth/login');
    req.flush(mockErrorResponse, { status: 401, statusText: 'Unauthorized' });
    expect(routerSpy.navigate).not.toHaveBeenCalled();
  });

  it('should store token in localStorage on successful login', () => {
    const mockResponse = {
      message: 'Login successful',
      Id: 1,
      email: 'test@example.com'
    };

    service.handleLogin(mockResponse);
    expect(localStorage.getItem('userEmail')).toBe('test@example.com');
    expect(localStorage.getItem('userId')).toBe('1');
  });

  it('should navigate to creation-projet on successful login', () => {
    const mockResponse = {
      message: 'Login successful',
      Id: 1,
      email: 'test@example.com'
    };

    service.handleLogin(mockResponse);
    expect(routerSpy.navigate).toHaveBeenCalledWith(['/creation-projet']);
  });

  it('should log out user and clear localStorage', () => {
    localStorage.setItem('userEmail', 'test@example.com');
    localStorage.setItem('userId', '1');
    service.logout();

    expect(service.checkAuthStatus()).toBeFalse(); // Vérifiez que l'utilisateur n'est plus authentifié
    expect(localStorage.getItem('userEmail')).toBeNull();
    expect(localStorage.getItem('userId')).toBeNull();
  });

  it('should return authenticated user ID', () => {
    service['currentUserId'] = 1; // Simuler un utilisateur authentifié
    service.getAuthenticatedUserId().subscribe(userId => {
      expect(userId).toBe(1);
    });
  });

  it('should return authenticated user email', () => {
    service['currentUserEmailSubject'].next('test@example.com'); // Simuler un email authentifié
    service.getAuthenticatedUserEmail().subscribe(email => {
      expect(email).toBe('test@example.com');
    });
  });

  // Ajoutez d'autres tests ici
});
