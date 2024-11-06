import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TaskService } from './task.service';
import { Task } from '../models/task.model';

describe('TaskService', () => {
  let service: TaskService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [TaskService]
    });
    service = TestBed.inject(TaskService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should retrieve tasks from the API via GET', () => {
    const dummyTasks: Task[] = [
      {
        id: 1,
        name: 'Task 1',          // Remplace 'title' par 'name'
        description: 'Description for Task 1',
        dueDate: '2024-12-01',
        priority: 'High',
        assignedBy: 'User A',
        assignedTo: 'User B',
        assignedUsers: ['User B'],
        status: 'Pending',
        project: {
            id: 1, name: 'Project 1',
            description: '',
            startDate: '',
            creatorUserId: null,
            creatorEmail: null,
            adminId: null
        }, // Assurez-vous que Project est défini
        createdBy: 'User A',
        updatedBy: null,
        createdAt: '2024-01-01',
        updatedAt: null
      },
      {
        id: 2,
        name: 'Task 2',          // Remplace 'title' par 'name'
        description: 'Description for Task 2',
        dueDate: '2024-12-02',
        priority: 'Medium',
        assignedBy: 'User A',
        assignedTo: 'User C',
        assignedUsers: ['User C'],
        status: 'Completed',
        project: {
            id: 1, name: 'Project 1',
            description: '',
            startDate: '',
            creatorUserId: null,
            creatorEmail: null,
            adminId: null
        }, // Assurez-vous que Project est défini
        createdBy: 'User A',
        updatedBy: null,
        createdAt: '2024-01-01',
        updatedAt: null
      }
    ];

    service.getTasks().subscribe(tasks => {
      expect(tasks.length).toBe(2);
      expect(tasks).toEqual(dummyTasks);
    });

    const request = httpMock.expectOne(service.apiUrl);
    expect(request.request.method).toBe('GET');
    request.flush(dummyTasks);
  });

  // Autres tests...
});
