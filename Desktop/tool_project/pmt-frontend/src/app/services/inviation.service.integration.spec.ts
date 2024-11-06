import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { InvitationService } from './invitation.service';

describe('InvitationService Integration Test', () => {
  let service: InvitationService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [InvitationService]
    });

    service = TestBed.inject(InvitationService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Vérifie qu'il n'y a pas de requêtes HTTP en attente
  });

  it('should send an invitation and return a successful response', () => {
    const projectId = '123';
    const memberData = { email: 'test@example.com' };
    const mockResponse = { message: 'Invitation sent successfully' };

    service.inviteMember(projectId, memberData).subscribe(response => {
      expect(response).toEqual(mockResponse); // Vérifie que la réponse est celle attendue
    });

    const req = httpMock.expectOne(`http://localhost:8080/auth/projects/${projectId}/invite`);
    expect(req.request.method).toEqual('POST'); // Vérifie que la méthode est POST
    req.flush(mockResponse); // Simule la réponse du serveur
  });

  it('should handle 400 Bad Request error', () => {
    const projectId = '123';
    const memberData = { email: 'test@example.com' };

    service.inviteMember(projectId, memberData).subscribe(
      () => fail('Expected an error, not an invitation'),
      error => {
        expect(error).toEqual('Bad Request'); // Vérifie que l'erreur est correctement gérée
      }
    );

    const req = httpMock.expectOne(`http://localhost:8080/auth/projects/${projectId}/invite`);
    req.flush('Bad Request', { status: 400, statusText: 'Bad Request' }); // Simule une erreur 400
  });
});
