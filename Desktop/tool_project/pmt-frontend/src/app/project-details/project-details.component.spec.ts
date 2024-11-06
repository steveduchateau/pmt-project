import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ProjectDetailsComponent } from './project-details.component';
import { ProjectService } from '../services/project.service';

describe('ProjectDetailsComponent', () => {
  let component: ProjectDetailsComponent;
  let fixture: ComponentFixture<ProjectDetailsComponent>;
  let snackBar: MatSnackBar;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProjectDetailsComponent, HttpClientTestingModule], // Import standalone component and HttpClientTestingModule
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: {
                get: (key: string) => {
                  if (key === 'id') {
                    return '1'; // Simulate the project ID you want to test
                  }
                  return null;
                }
              }
            }
          }
        },
        ProjectService,
        MatSnackBar
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(ProjectDetailsComponent);
    component = fixture.componentInstance;
    snackBar = TestBed.inject(MatSnackBar);
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should load project details on init', () => {
    spyOn(component, 'loadProjectDetails').and.callThrough(); // Spy on the method to ensure it gets called
    component.ngOnInit(); // Call ngOnInit to trigger loading of project details
    expect(component.loadProjectDetails).toHaveBeenCalled(); // Check if the method was called
  });

  // Add other tests as needed
});
