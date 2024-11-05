// src/app/services/user.service.spec.ts
import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UserService } from './user.service';

describe('UserService', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  const mockUser = { email: 'test@example.com', password: 'password' };
  const mockUserData = { username: 'testUser', email: 'test@example.com', password: 'password' };
  const mockResponse = { message: 'User registered successfully!' };
  const mockLoginResponse = { token: '12345' }; // Mock login response for testing login

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should login user successfully', () => {
    service.login(mockUser).subscribe(response => {
      expect(response).toEqual(mockLoginResponse);
    });

    const req = httpMock.expectOne(`${service.apiUrl}/login`);
    expect(req.request.method).toBe('POST');
    req.flush(mockLoginResponse);
  });

  it('should handle error on login (404)', () => {
    service.login(mockUser).subscribe(
      () => fail('expected an error, not user data'),
      error => expect(error).toContain('Error Code: 404')
    );

    const req = httpMock.expectOne(`${service.apiUrl}/login`);
    req.flush('404 error', { status: 404, statusText: 'Not Found' });
  });

  it('should handle error on login (401)', () => {
    service.login(mockUser).subscribe(
      () => fail('expected an error, not user data'),
      error => expect(error).toContain('Error Code: 401')
    );

    const req = httpMock.expectOne(`${service.apiUrl}/login`);
    req.flush('401 error', { status: 401, statusText: 'Unauthorized' });
  });

  it('should register user successfully', () => {
    service.registerUser(mockUserData).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne(`${service.apiUrl}/register`); // URL corrigée
    expect(req.request.method).toBe('POST');
    req.flush(mockResponse);
  });

  it('should handle error on registration (500)', () => {
    service.registerUser(mockUserData).subscribe(
      () => fail('expected an error, not user data'),
      error => expect(error).toContain('Error Code: 500')
    );

    const req = httpMock.expectOne(`${service.apiUrl}/register`); // URL corrigée
    req.flush('500 error', { status: 500, statusText: 'Server Error' });
  });

  it('should handle error on registration (400)', () => {
    // Test with invalid data
    service.registerUser({}).subscribe(
      () => fail('expected an error, not user data'),
      error => expect(error).toContain('Error Code: 400')
    );

    const req = httpMock.expectOne(`${service.apiUrl}/register`); // URL corrigée
    req.flush('400 error', { status: 400, statusText: 'Bad Request' });
  });

  it('should call the API with correct user data on registration', () => {
    service.registerUser(mockUserData).subscribe();

    const req = httpMock.expectOne(`${service.apiUrl}/register`); // URL corrigée
    expect(req.request.body).toEqual(mockUserData); // Vérifiez que les données utilisateur correctes sont envoyées
  });

  it('should handle already registered user during registration', () => {
    service.registerUser(mockUserData).subscribe(
      () => fail('expected an error, not user data'),
      error => {
        expect(error).toContain('Error Code: 409');
        expect(error).toContain('Email already in use'); // Assurez-vous que le message correspond à votre API
      }
    );

    const req = httpMock.expectOne(`${service.apiUrl}/register`); // URL corrigée
    req.flush({ message: 'Email already in use' }, { status: 409, statusText: 'Conflict' });
  });
});
