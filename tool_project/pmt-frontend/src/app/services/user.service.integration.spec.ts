// src/app/services/user.service.integration.spec.ts
import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UserService } from './user.service';

describe('UserService', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });
    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Vérifie qu'aucune requête HTTP non traitée n'existe
  });

  it('should login a user and return user data', () => {
    const dummyUser = { email: 'test@example.com', password: 'password' };
    const dummyResponse = { token: 'abcd1234', user: { id: 1, email: 'test@example.com' } };

    service.login(dummyUser).subscribe(response => {
      expect(response).toEqual(dummyResponse);
    });

    const request = httpMock.expectOne(`${service.apiUrl}/login`);
    expect(request.request.method).toBe('POST');
    request.flush(dummyResponse); // Simule la réponse de l'API
  });

  it('should register a new user and return success message', () => {
    const newUser = { email: 'newuser@example.com', password: 'newpassword' };
    const dummyResponse = { message: 'User registered successfully' };

    service.registerUser(newUser).subscribe(response => {
      expect(response).toEqual(dummyResponse);
    });

    const request = httpMock.expectOne(`${service.apiUrl}/register`);
    expect(request.request.method).toBe('POST');
    request.flush(dummyResponse); // Simule la réponse de l'API
  });

});
