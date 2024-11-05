import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ProjectService } from './project.service';
import { Project } from '../models/project';

describe('ProjectService Integration Test', () => {
  let service: ProjectService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ProjectService]
    });

    service = TestBed.inject(ProjectService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Vérifie qu'il n'y a pas de requêtes HTTP en attente
  });

  it('should create a project and return it', () => {
    const mockProject: Project = {
        id: 1, name: 'New Project', description: 'Project Description',
        startDate: '',
        creatorUserId: null,
        creatorEmail: null,
        adminId: null
    };
    const userId = '123';
    localStorage.setItem('userId', userId); // Simuler l'ID de l'utilisateur dans le localStorage

    service.createProject(mockProject, Number(userId)).subscribe(response => {
      expect(response).toEqual(mockProject); // Vérifie que la réponse est celle attendue
    });

    const req = httpMock.expectOne('http://localhost:8080/auth/projects');
    expect(req.request.method).toEqual('POST'); // Vérifie que la méthode est POST
    expect(req.request.headers.get('User-ID')).toEqual(userId); // Vérifie l'en-tête User-ID
    req.flush(mockProject); // Simule la réponse du serveur
  });

  it('should fetch projects and return them', () => {
    const mockProjects: Project[] = [
      {
          id: 1, name: 'Project 1', description: 'Description 1',
          startDate: '',
          creatorUserId: null,
          creatorEmail: null,
          adminId: null
      },
      {
          id: 2, name: 'Project 2', description: 'Description 2',
          startDate: '',
          creatorUserId: null,
          creatorEmail: null,
          adminId: null
      }
    ];

    service.getProjects().subscribe(response => {
      expect(response).toEqual(mockProjects); // Vérifie que la réponse est celle attendue
    });

    const req = httpMock.expectOne('http://localhost:8080/auth/projects');
    expect(req.request.method).toEqual('GET'); // Vérifie que la méthode est GET
    req.flush(mockProjects); // Simule la réponse du serveur
  });

  it('should invite a member to a project and return a success message', () => {
    const projectId = 1;
    const email = 'test@example.com';
    const role = 'developer';
    const mockResponse = { message: 'Invitation sent successfully' };

    service.inviteMember(projectId, email, role).subscribe(response => {
      expect(response).toEqual(mockResponse); // Vérifie que la réponse est celle attendue
    });

    const req = httpMock.expectOne(`http://localhost:8080/auth/projects/${projectId}/invite`);
    expect(req.request.method).toEqual('POST'); // Vérifie que la méthode est POST
    req.flush(mockResponse); // Simule la réponse du serveur
  });
});
