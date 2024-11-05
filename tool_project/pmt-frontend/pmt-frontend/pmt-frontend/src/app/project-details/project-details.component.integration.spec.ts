import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ProjectDetailsComponent } from './project-details.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { ProjectService } from '../services/project.service';
import { Project } from '../models/project';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('ProjectDetailsComponent Integration Test', () => {
  let component: ProjectDetailsComponent;
  let fixture: ComponentFixture<ProjectDetailsComponent>;
  let projectService: jasmine.SpyObj<ProjectService>;

  beforeEach(async () => {
    projectService = jasmine.createSpyObj('ProjectService', ['getProject']);
    
    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        BrowserAnimationsModule,
        ProjectDetailsComponent // Importez le composant ici, pas dans les déclarations
      ],
      providers: [
        { provide: ActivatedRoute, useValue: { snapshot: { paramMap: { get: () => '1' } } } },
        { provide: ProjectService, useValue: projectService },
        MatSnackBar // Ajoutez ici si nécessaire pour gérer les notifications
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(ProjectDetailsComponent);
    component = fixture.componentInstance;
  });

  it('should load project details on init', () => {
    const mockProject: Project = {
      id: 1,
      name: 'Test Project',
      description: 'Description of the test project',
      startDate: new Date().toISOString(), // Assurez-vous que la date est au bon format
      creatorUserId: 123,
      creatorEmail: 'creator@example.com',
      adminId: 456,
    };

    projectService.getProject.and.returnValue(of(mockProject)); // Simule le retour du service

    component.ngOnInit(); // Appel de ngOnInit pour charger les données
    fixture.detectChanges(); // Déclenche la détection des changements
  });
});
