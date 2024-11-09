import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TaskDashboardComponent } from './task-dashboard.component';
import { TaskService } from '../services/task.service';
import { ProjectService } from '../services/project.service';
import { of } from 'rxjs';
import { Task } from '../models/task.model';
import { Project } from '../models/project';

describe('TaskDashboardComponent Integration Test', () => {
  let component: TaskDashboardComponent;
  let fixture: ComponentFixture<TaskDashboardComponent>;
  let taskServiceMock: jasmine.SpyObj<TaskService>;
  let projectServiceMock: jasmine.SpyObj<ProjectService>;

  beforeEach(async () => {
    // Créer des mocks pour TaskService et ProjectService
    taskServiceMock = jasmine.createSpyObj('TaskService', ['getTasks']);
    projectServiceMock = jasmine.createSpyObj('ProjectService', ['getProjects']);

    // Configurer le module de test
    await TestBed.configureTestingModule({
      imports: [TaskDashboardComponent], // Importer le composant ici
      providers: [
        { provide: TaskService, useValue: taskServiceMock },
        { provide: ProjectService, useValue: projectServiceMock },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskDashboardComponent);
    component = fixture.componentInstance;
  });

  it('should load tasks and projects on init', () => {
    // Simuler les données de retour pour les tâches et les projets
    const dummyTasks: Task[] = [
      { id: 1, name: 'Task 1', description: '', dueDate: '', priority: '', assignedBy: '', assignedTo: '', assignedUsers: [], status: 'Non démarrée', project: { name: 'Project 1', id: 0, description: '', startDate: '', creatorUserId: null, creatorEmail: null, adminId: null }, createdBy: null, updatedBy: null, createdAt: null, updatedAt: null },
      { id: 2, name: 'Task 2', description: '', dueDate: '', priority: '', assignedBy: '', assignedTo: '', assignedUsers: [], status: 'En cours', project: { name: 'Project 1', id: 0, description: '', startDate: '', creatorUserId: null, creatorEmail: null, adminId: null }, createdBy: null, updatedBy: null, createdAt: null, updatedAt: null },
    ];
    const dummyProjects: Project[] = [
      { id: 1, name: 'Project 1', description: '', startDate: '', creatorUserId: null, creatorEmail: null, adminId: null },
      { id: 2, name: 'Project 2', description: '', startDate: '', creatorUserId: null, creatorEmail: null, adminId: null },
    ];

    // Configurer les mocks pour retourner les données
    taskServiceMock.getTasks.and.returnValue(of(dummyTasks));
    projectServiceMock.getProjects.and.returnValue(of(dummyProjects));

    // Appeler ngOnInit
    component.ngOnInit();
    fixture.detectChanges();

    // Vérifier que les tâches et projets sont chargés correctement
    expect(component.pendingTasks.length).toBe(1); // Vérifie les tâches en attente
    expect(component.inProgressTasks.length).toBe(1); // Vérifie les tâches en cours
    expect(component.projects.length).toBe(2); // Vérifie le nombre de projets
  });
});
