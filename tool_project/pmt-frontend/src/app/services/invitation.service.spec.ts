import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { InvitationService } from './invitation.service';

describe('InvitationService', () => {
  let service: InvitationService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(InvitationService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should add an invitation', () => {
    service.addInvitation('Invitation 1');
    expect(service.getInvitations()).toContain('Invitation 1');
  });

  it('should return all invitations', () => {
    service.addInvitation('Invitation 1');
    service.addInvitation('Invitation 2');
    expect(service.getInvitations().length).toBe(2);
    expect(service.getInvitations()).toEqual(['Invitation 1', 'Invitation 2']);
  });

  it('should clear all invitations', () => {
    service.addInvitation('Invitation 1');
    service.clearInvitations();
    expect(service.getInvitations().length).toBe(0);
  });

  it('should not add duplicate invitations', () => {
    service.addInvitation('Invitation 1');
    service.addInvitation('Invitation 1'); // Ajout en double
    expect(service.getInvitations().length).toBe(1); // Toujours 1 invitation
    expect(service.getInvitations()).toEqual(['Invitation 1']);
  });

  it('should not add empty invitations', () => {
    service.addInvitation(''); // Ajout d'une chaîne vide
    expect(service.getInvitations().length).toBe(0); // Pas d'invitation ajoutée
  });

  it('should return an empty list if no invitations have been added', () => {
    expect(service.getInvitations().length).toBe(0); // Aucune invitation dans la liste
    expect(service.getInvitations()).toEqual([]); // La liste doit être vide
  });

  it('should not throw an error when clearing invitations if none exist', () => {
    expect(() => service.clearInvitations()).not.toThrow(); // Pas d'erreur
    expect(service.getInvitations().length).toBe(0); // Toujours vide après
  });

  // Tests pour inviter un membre
  it('should invite a member successfully', () => {
    const projectId = '12345';
    const memberData = { email: 'member@example.com' };
    service.inviteMember(projectId, memberData).subscribe(response => {
      expect(response).toEqual({ success: true });
    });
    const req = httpMock.expectOne(`http://localhost:8080/auth/projects/${projectId}/invite`);
    expect(req.request.method).toBe('POST');
    req.flush({ success: true });
  });

  it('should handle error on invite (400)', () => {
    const projectId = '12345';
    const memberData = { email: 'member@example.com' };
    service.inviteMember(projectId, memberData).subscribe(
      response => fail('should have failed with a 400 error'),
      error => {
        expect(error).toBe('Bad Request');
      }
    );
    const req = httpMock.expectOne(`http://localhost:8080/auth/projects/${projectId}/invite`);
    expect(req.request.method).toBe('POST');
    req.flush('Bad Request', { status: 400, statusText: 'Bad Request' });
  });

  it('should handle error on invite (500)', () => {
    const projectId = '12345';
    const memberData = { email: 'member@example.com' };
    service.inviteMember(projectId, memberData).subscribe(
      response => fail('should have failed with a 500 error'),
      error => {
        expect(error).toBe('Server Error');
      }
    );
    const req = httpMock.expectOne(`http://localhost:8080/auth/projects/${projectId}/invite`);
    expect(req.request.method).toBe('POST');
    req.flush('Server Error', { status: 500, statusText: 'Server Error' });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
