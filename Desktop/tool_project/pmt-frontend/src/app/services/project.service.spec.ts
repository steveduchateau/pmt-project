import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ProjectService } from './project.service';
import { Project } from '../models/project';

describe('ProjectService', () => {
  let service: ProjectService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(ProjectService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should create a project', () => {
    const mockProject: Project = {
      id: 1,
      name: 'Test Project',
      description: 'This is a test project',
      startDate: '2024-01-01' // Remplacez par une date appropriée
      ,
      creatorUserId: null,
      creatorEmail: null,
      adminId: null
    };
    const userId = '123';
    localStorage.setItem('userId', userId);

    service.createProject(mockProject, parseInt(userId)).subscribe(response => {
      expect(response).toEqual(mockProject);
    });

    const req = httpMock.expectOne(service.apiUrl);
    expect(req.request.method).toBe('POST');
    expect(req.request.headers.get('User-ID')).toBe(userId);
    req.flush(mockProject);
  });

  it('should get all projects', () => {
    const mockProjects: Project[] = [
      {
        id: 1, name: 'Project 1', description: 'First project', startDate: '2024-01-01',
        creatorUserId: null,
        creatorEmail: null,
        adminId: null
      },
      {
        id: 2, name: 'Project 2', description: 'Second project', startDate: '2024-02-01',
        creatorUserId: null,
        creatorEmail: null,
        adminId: null
      }
    ];

    service.getProjects().subscribe(projects => {
      expect(projects.length).toBe(2);
      expect(projects).toEqual(mockProjects);
    });

    const req = httpMock.expectOne(service.apiUrl);
    expect(req.request.method).toBe('GET');
    req.flush(mockProjects);
  });

  it('should get a project by id', () => {
    const mockProject: Project = {
      id: 1,
      name: 'Test Project',
      description: 'This is a test project',
      startDate: '2024-01-01',
      creatorUserId: null,
      creatorEmail: null,
      adminId: null
    };

    service.getProject(1).subscribe(project => {
      expect(project).toEqual(mockProject);
    });

    const req = httpMock.expectOne(`${service.apiUrl}/1`);
    expect(req.request.method).toBe('GET');
    req.flush(mockProject);
  });

  it('should check if user is admin', () => {
    const projectId = 1;
    const userId = 123;

    service.isUserAdmin(projectId, userId).subscribe(isAdmin => {
      expect(isAdmin).toBeTrue();
    });

    const req = httpMock.expectOne(`${service.apiUrl}/${projectId}/isAdmin/${userId}`);
    expect(req.request.method).toBe('GET');
    req.flush(true);
  });

  it('should invite a member', () => {
    const projectId = 1;
    const email = 'test@example.com';
    const role = 'member';

    service.inviteMember(projectId, email, role).subscribe(response => {
      expect(response).toBeTruthy();
    });

    const req = httpMock.expectOne(`${service.apiUrl}/${projectId}/invite`);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual({ email, role });
    req.flush({});
  });

  it('should assign a task', () => {
    const taskId = 1;
    const assignedTo = 'test@example.com';

    service.assignTask(taskId, assignedTo).subscribe(response => {
      expect(response).toBeTruthy();
    });

    const req = httpMock.expectOne(`${service.apiUrl}/tasks/${taskId}/assign`);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual({ assignedTo });
    req.flush({});
  });

  it('should delete a project member', () => {
    const projectId = 1;
    const memberId = 2;

    service.deleteProjectMember(projectId, memberId).subscribe(() => {
      // Pas besoin de vérifier la réponse ici car on s'attend à void
    });

    const req = httpMock.expectOne(`${service.apiUrl}/${projectId}/members/${memberId}`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null); // Simule une réponse vide (null)
  });

  // Nouveau test : should handle error on create project
  it('should handle error on create project', () => {
    const mockProject: Project = {
      id: 1,
      name: 'Test Project',
      description: 'This is a test project',
      startDate: '2024-01-01',
      creatorUserId: null,
      creatorEmail: null,
      adminId: null
    };
    const userId = '123';
    localStorage.setItem('userId', userId);

    service.createProject(mockProject, parseInt(userId)).subscribe(
      () => fail('expected an error, not a project'),
      error => {
        expect(error).toBeTruthy();
      }
    );

    const req = httpMock.expectOne(service.apiUrl);
    expect(req.request.method).toBe('POST');
    expect(req.request.headers.get('User-ID')).toBe(userId);
    req.flush('Something went wrong', { status: 500, statusText: 'Server Error' });
  });

  // Nouveau test : should handle error on get all projects
  it('should handle error on get all projects', () => {
    service.getProjects().subscribe(
      () => fail('expected an error, not projects'),
      error => {
        expect(error).toBeTruthy();
      }
    );

    const req = httpMock.expectOne(service.apiUrl);
    expect(req.request.method).toBe('GET');
    req.flush('Something went wrong', { status: 500, statusText: 'Server Error' });
  });

  // Nouveau test : should handle error on get project by id
  it('should handle error on get project by id', () => {
    service.getProject(1).subscribe(
      () => fail('expected an error, not a project'),
      error => {
        expect(error).toBeTruthy();
      }
    );

    const req = httpMock.expectOne(`${service.apiUrl}/1`);
    expect(req.request.method).toBe('GET');
    req.flush('Something went wrong', { status: 500, statusText: 'Server Error' });
  });
});
