import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TaskService } from './task.service';
import { Task } from '../models/task.model';
import { Project } from '../models/project';

describe('TaskService', () => {
  let service: TaskService;
  let httpMock: HttpTestingController;

  const mockTasks: Task[] = [
    {
      id: 1,
      name: 'Test Task 1',
      description: 'Description for task 1',
      dueDate: '2024-12-31',
      priority: 'High',
      assignedBy: 'User A',
      assignedTo: 'User B',
      assignedUsers: ['User B', 'User C'],
      status: 'In Progress',
      project: {
        id: 1,
        name: 'Test Project',
      } as Project,
      createdBy: 'User A',
      updatedBy: null,
      createdAt: '2024-01-01T12:00:00Z',
      updatedAt: null,
      history: []
    },
    {
      id: 2,
      name: 'Test Task 2',
      description: 'Description for task 2',
      dueDate: '2024-11-30',
      priority: 'Medium',
      assignedBy: 'User B',
      assignedTo: 'User A',
      assignedUsers: ['User A'],
      status: 'Completed',
      project: {
        id: 2,
        name: 'Another Project',
      } as Project,
      createdBy: 'User B',
      updatedBy: 'User C',
      createdAt: '2024-01-02T12:00:00Z',
      updatedAt: '2024-01-03T12:00:00Z',
      history: []
    }
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(TaskService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should retrieve all tasks from the API', () => {
    service.getTasks().subscribe(tasks => {
      expect(tasks.length).toBe(2);
      expect(tasks).toEqual(mockTasks);
    });

    const req = httpMock.expectOne(service.apiUrl);
    expect(req.request.method).toBe('GET');
    req.flush(mockTasks);
  });

  it('should handle error when retrieving tasks', () => {
    service.getTasks().subscribe(
      () => fail('expected an error, not tasks'),
      error => expect(error.message).toContain('404')
    );

    const req = httpMock.expectOne(service.apiUrl);
    req.flush('404 error', { status: 404, statusText: 'Not Found' });
  });

  it('should retrieve tasks by status', () => {
    const status = 'completed';
    service.getTasksByStatus(status).subscribe(tasks => {
      expect(tasks.length).toBe(1);
      expect(tasks).toEqual([mockTasks[1]]);
    });

    const req = httpMock.expectOne(`${service.apiUrl}?status=${status}`);
    expect(req.request.method).toBe('GET');
    req.flush([mockTasks[1]]);
  });

  it('should handle error when retrieving tasks by status', () => {
    const status = 'unknown';
    service.getTasksByStatus(status).subscribe(
      () => fail('expected an error, not tasks'),
      error => expect(error.message).toContain('404')
    );

    const req = httpMock.expectOne(`${service.apiUrl}?status=${status}`);
    req.flush('404 error', { status: 404, statusText: 'Not Found' });
  });

  it('should add a new task', () => {
    const newTask: Task = {
      id: 3,
      name: 'New Task',
      description: 'Description for new task',
      dueDate: '2024-12-25',
      priority: 'Low',
      assignedBy: 'User C',
      assignedTo: 'User D',
      assignedUsers: ['User D'],
      status: 'Pending',
      project: {
        id: 3,
        name: 'Third Project',
      } as Project,
      createdBy: 'User C',
      updatedBy: null,
      createdAt: '2024-01-05T12:00:00Z',
      updatedAt: null,
      history: []
    };

    service.addTask(newTask).subscribe(task => {
      expect(task).toEqual(newTask);
    });

    const req = httpMock.expectOne(service.apiUrl);
    expect(req.request.method).toBe('POST');
    req.flush(newTask);
  });

  it('should handle error when adding a new task', () => {
    const newTask: Task = {
      id: 3,
      name: 'New Task',
      description: 'Description for new task',
      dueDate: '2024-12-25',
      priority: 'Low',
      assignedBy: 'User C',
      assignedTo: 'User D',
      assignedUsers: ['User D'],
      status: 'Pending',
      project: {
        id: 3,
        name: 'Third Project',
      } as Project,
      createdBy: 'User C',
      updatedBy: null,
      createdAt: '2024-01-05T12:00:00Z',
      updatedAt: null,
      history: []
    };

    service.addTask(newTask).subscribe(
      () => fail('expected an error, not task'),
      error => expect(error.message).toContain('500')
    );

    const req = httpMock.expectOne(service.apiUrl);
    expect(req.request.method).toBe('POST');
    req.flush('500 error', { status: 500, statusText: 'Server Error' });
  });

  it('should update an existing task', () => {
    const updatedTask: Task = { ...mockTasks[0], name: 'Updated Task' };

    service.updateTask(1, 1, updatedTask).subscribe(task => {
      expect(task).toEqual(updatedTask);
    });

    const req = httpMock.expectOne(`${service.apiUrl}/1/tasks/1`);
    expect(req.request.method).toBe('PUT');
    req.flush(updatedTask);
  });

  it('should handle error when updating a task', () => {
    const updatedTask: Task = { ...mockTasks[0], name: 'Updated Task' };

    service.updateTask(1, 1, updatedTask).subscribe(
      () => fail('expected an error, not task'),
      error => expect(error.message).toContain('500')
    );

    const req = httpMock.expectOne(`${service.apiUrl}/1/tasks/1`);
    expect(req.request.method).toBe('PUT');
    req.flush('500 error', { status: 500, statusText: 'Server Error' });
  });

  it('should retrieve task history', () => {
    const taskId = 1;
    const mockHistory = [
      { id: 1, taskId, change: 'Status changed to In Progress' },
      { id: 2, taskId, change: 'Assigned to User B' },
    ];

    service.getTaskHistory(taskId).subscribe(history => {
      expect(history).toEqual(mockHistory);
    });

    const req = httpMock.expectOne(`${service.apiUrl}/${taskId}/history`);
    expect(req.request.method).toBe('GET');
    req.flush(mockHistory);
  });

  it('should handle error when retrieving task history for a nonexistent task', () => {
    const taskId = 999; // Un ID de tâche qui n'existe pas
    service.getTaskHistory(taskId).subscribe(
      () => fail('expected an error, not history'),
      error => expect(error.message).toContain('404')
    );

    const req = httpMock.expectOne(`${service.apiUrl}/${taskId}/history`);
    expect(req.request.method).toBe('GET');
    req.flush('404 error', { status: 404, statusText: 'Not Found' });
  });

  it('should retrieve tasks by project ID', () => {
    const projectId = 1;
    service.getTasksByProjectId(projectId).subscribe(tasks => {
      expect(tasks.length).toBe(1);
      expect(tasks).toEqual([mockTasks[0]]);
    });

    const req = httpMock.expectOne(`${service.apiUrl}/${projectId}/tasks`);
    expect(req.request.method).toBe('GET');
    req.flush([mockTasks[0]]);
  });

  it('should handle error when retrieving tasks by nonexistent project ID', () => {
    const projectId = 999; // Un ID de projet qui n'existe pas
    service.getTasksByProjectId(projectId).subscribe(
      () => fail('expected an error, not tasks'),
      error => expect(error.message).toContain('404')
    );

    const req = httpMock.expectOne(`${service.apiUrl}/${projectId}/tasks`);
    expect(req.request.method).toBe('GET');
    req.flush('404 error', { status: 404, statusText: 'Not Found' });
  });
});
