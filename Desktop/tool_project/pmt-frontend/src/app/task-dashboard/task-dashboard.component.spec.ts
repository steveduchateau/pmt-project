import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TaskDashboardComponent } from './task-dashboard.component';
import { TaskService } from '../services/task.service';
import { ProjectService } from '../services/project.service';
import { of, throwError } from 'rxjs';
import { Task } from '../models/task.model';
import { Project } from '../models/project';

describe('TaskDashboardComponent', () => {
  let component: TaskDashboardComponent;
  let fixture: ComponentFixture<TaskDashboardComponent>;
  let taskServiceMock: jasmine.SpyObj<TaskService>;
  let projectServiceMock: jasmine.SpyObj<ProjectService>;

  beforeEach(async () => {
    taskServiceMock = jasmine.createSpyObj('TaskService', ['getTasks', 'getTaskHistory']);
    projectServiceMock = jasmine.createSpyObj('ProjectService', ['getProjects']);

    await TestBed.configureTestingModule({
      imports: [TaskDashboardComponent], // Importing the standalone component here
      providers: [
        { provide: TaskService, useValue: taskServiceMock },
        { provide: ProjectService, useValue: projectServiceMock }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(TaskDashboardComponent);
    component = fixture.componentInstance;
  });

  it('should load tasks and projects on init', () => {
    const mockTasks: Task[] = [
      {
        id: 1,
        name: 'Task 1',
        description: 'Description 1',
        dueDate: '2024-12-31',
        priority: 'High',
        assignedBy: 'User A',
        assignedTo: 'User B',
        assignedUsers: [],
        status: 'Non démarrée',
        project: {
          id: 1, name: 'Project 1', description: '', startDate: '',
          creatorUserId: null,
          creatorEmail: null,
          adminId: null
        },
        createdBy: null,
        updatedBy: null,
        createdAt: null,
        updatedAt: null
      },
      {
        id: 2,
        name: 'Task 2',
        description: 'Description 2',
        dueDate: '2024-12-31',
        priority: 'Medium',
        assignedBy: 'User A',
        assignedTo: 'User C',
        assignedUsers: [],
        status: 'En cours',
        project: {
          id: 1, name: 'Project 1', description: '', startDate: '',
          creatorUserId: null,
          creatorEmail: null,
          adminId: null
        },
        createdBy: null,
        updatedBy: null,
        createdAt: null,
        updatedAt: null
      }
    ];

    const mockProjects: Project[] = [
      {
        id: 1, name: 'Project 1', description: '', startDate: '',
        creatorUserId: null,
        creatorEmail: null,
        adminId: null
      },
      {
        id: 2, name: 'Project 2', description: '', startDate: '',
        creatorUserId: null,
        creatorEmail: null,
        adminId: null
      }
    ];

    taskServiceMock.getTasks.and.returnValue(of(mockTasks));
    projectServiceMock.getProjects.and.returnValue(of(mockProjects));

    component.ngOnInit();

    expect(taskServiceMock.getTasks).toHaveBeenCalled();
    expect(projectServiceMock.getProjects).toHaveBeenCalled();
    expect(component.pendingTasks.length).toBe(1); // 1 task with status 'Non démarrée'
    expect(component.inProgressTasks.length).toBe(1); // 1 task with status 'En cours'
    expect(component.projects.length).toBe(2);
  });

  it('should handle empty projects list correctly', () => {
    taskServiceMock.getTasks.and.returnValue(of([])); // Return an empty array for tasks
    projectServiceMock.getProjects.and.returnValue(of([])); // Return an empty array for projects

    component.ngOnInit();

    expect(taskServiceMock.getTasks).toHaveBeenCalled();
    expect(projectServiceMock.getProjects).toHaveBeenCalled();
    expect(component.pendingTasks.length).toBe(0);
    expect(component.inProgressTasks.length).toBe(0);
    expect(component.projects.length).toBe(0);
  });

  it('should handle error when loading tasks', () => {
    spyOn(console, 'error');

    taskServiceMock.getTasks.and.returnValue(throwError(() => new Error('Erreur lors de la récupération des tâches')));
    projectServiceMock.getProjects.and.returnValue(of([])); // Return an empty array for projects

    component.ngOnInit();

    expect(taskServiceMock.getTasks).toHaveBeenCalled();
    expect(console.error).toHaveBeenCalledWith('Erreur lors de la récupération des tâches', jasmine.any(Error));
  });

  it('should filter tasks based on selected project', () => {
    component.selectedProjectName = 'Project 1'; // Set the selected project
  
    const mockTasks: Task[] = [
      {
        id: 1,
        name: 'Task 1',
        description: 'Description 1',
        dueDate: '2024-12-31',
        priority: 'High',
        assignedBy: 'User A',
        assignedTo: 'User B',
        assignedUsers: [],
        status: 'Non démarrée',
        project: {
          id: 1, name: 'Project 1', description: '', startDate: '',
          creatorUserId: null,
          creatorEmail: null,
          adminId: null
        },
        createdBy: null,
        updatedBy: null,
        createdAt: null,
        updatedAt: null
      },
      {
        id: 2,
        name: 'Task 2',
        description: 'Description 2',
        dueDate: '2024-12-31',
        priority: 'Medium',
        assignedBy: 'User A',
        assignedTo: 'User C',
        assignedUsers: [],
        status: 'En cours',
        project: {
          id: 1, name: 'Project 1', description: '', startDate: '',
          creatorUserId: null,
          creatorEmail: null,
          adminId: null
        },
        createdBy: null,
        updatedBy: null,
        createdAt: null,
        updatedAt: null
      },
      {
        id: 3,
        name: 'Task 3',
        description: 'Description 3',
        dueDate: '2024-12-31',
        priority: 'Low',
        assignedBy: 'User A',
        assignedTo: 'User D',
        assignedUsers: [],
        status: 'Terminée',
        project: {
          id: 2, name: 'Project 2', description: '', startDate: '',
          creatorUserId: null,
          creatorEmail: null,
          adminId: null
        },
        createdBy: null,
        updatedBy: null,
        createdAt: null,
        updatedAt: null
      }
    ];
  
    component.filterTasks(mockTasks);
  
    // Correction ici
    expect(component.pendingTasks.length).toBe(1); // 1 task with status 'Non démarrée'
    expect(component.inProgressTasks.length).toBe(1); // 1 task with status 'En cours'
    expect(component.completedTasks.length).toBe(0); // 0 completed tasks
  });
  
  it('should filter tasks when "All" project is selected', () => {
    component.selectedProjectName = 'All'; // Set the selected project to 'All'

    const mockTasks: Task[] = [
      {
        id: 1,
        name: 'Task 1',
        description: 'Description 1',
        dueDate: '2024-12-31',
        priority: 'High',
        assignedBy: 'User A',
        assignedTo: 'User B',
        assignedUsers: [],
        status: 'Non démarrée',
        project: {
          id: 1, name: 'Project 1', description: '', startDate: '',
          creatorUserId: null,
          creatorEmail: null,
          adminId: null
        },
        createdBy: null,
        updatedBy: null,
        createdAt: null,
        updatedAt: null
      },
      {
        id: 2,
        name: 'Task 2',
        description: 'Description 2',
        dueDate: '2024-12-31',
        priority: 'Medium',
        assignedBy: 'User A',
        assignedTo: 'User C',
        assignedUsers: [],
        status: 'En cours',
        project: {
          id: 1, name: 'Project 1', description: '', startDate: '',
          creatorUserId: null,
          creatorEmail: null,
          adminId: null
        },
        createdBy: null,
        updatedBy: null,
        createdAt: null,
        updatedAt: null
      },
      {
        id: 3,
        name: 'Task 3',
        description: 'Description 3',
        dueDate: '2024-12-31',
        priority: 'Low',
        assignedBy: 'User A',
        assignedTo: 'User D',
        assignedUsers: [],
        status: 'Terminée',
        project: {
          id: 2, name: 'Project 2', description: '', startDate: '',
          creatorUserId: null,
          creatorEmail: null,
          adminId: null
        },
        createdBy: null,
        updatedBy: null,
        createdAt: null,
        updatedAt: null
      }
    ];

    component.filterTasks(mockTasks);

    expect(component.pendingTasks.length).toBe(1); // 1 task with status 'Non démarrée'
    expect(component.inProgressTasks.length).toBe(1); // 1 task with status 'En cours'
    expect(component.completedTasks.length).toBe(1); // 1 completed task
  });
});
