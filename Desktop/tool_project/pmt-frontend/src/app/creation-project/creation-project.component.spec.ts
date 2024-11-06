import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { CreateProjectComponent } from './creation-project.component';
import { ProjectService } from '../services/project.service';
import { AuthService } from '../services/auth.service';
import { of, throwError } from 'rxjs';
import { Router } from '@angular/router';

describe('CreateProjectComponent', () => {
  let component: CreateProjectComponent;
  let fixture: ComponentFixture<CreateProjectComponent>;
  let projectService: ProjectService;
  let authService: AuthService;
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        CreateProjectComponent,
        ReactiveFormsModule,
        HttpClientTestingModule,
        RouterTestingModule.withRoutes([{ path: 'connexion', redirectTo: '' }]) // Ajout de la route 'connexion'
      ],
      providers: [
        ProjectService,
        AuthService
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CreateProjectComponent);
    component = fixture.componentInstance;
    projectService = TestBed.inject(ProjectService);
    authService = TestBed.inject(AuthService);
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize the form with empty fields', () => {
    expect(component.projectForm.value).toEqual({
      name: '',
      description: '',
      startDate: ''
    });
  });

  it('should not allow invalid dates', () => {
    const startDateControl = component.projectForm.get('startDate');
    startDateControl?.setValue('invalid-date');
    expect(startDateControl?.hasError('invalidDate')).toBeTrue();
  });

  it('should redirect to login if user is not authenticated', () => {
    spyOn(authService, 'checkAuthStatus').and.returnValue(false);
    spyOn(router, 'navigate');

    component.ngOnInit();

    expect(router.navigate).toHaveBeenCalledWith(['/connexion']);
  });

  it('should show an error if user ID cannot be retrieved', fakeAsync(() => {
    spyOn(authService, 'getAuthenticatedUserEmail').and.returnValue(of('user@example.com'));
    spyOn(authService, 'getAuthenticatedUserId').and.returnValue(of(null));
    spyOn(router, 'navigate');

    component.ngOnInit();
    tick();

    expect(router.navigate).toHaveBeenCalledWith(['/connexion']);
  }));

  it('should not submit the form if user ID is missing', () => {
    spyOn(router, 'navigate');
    component.currentUserId = null;

    component.onSubmit();

    expect(router.navigate).toHaveBeenCalledWith(['/connexion']);
  });

  it('should display an error message on project creation failure', fakeAsync(() => {
    spyOn(projectService, 'createProject').and.returnValue(throwError(() => new Error('Error')));
    spyOn(window, 'alert');

    component.currentUserEmail = 'user@example.com';
    component.currentUserId = 1;
    component.projectForm.setValue({
      name: 'Test Project',
      description: 'Description',
      startDate: '2023-10-10'
    });

    component.onSubmit();
    tick();

    expect(window.alert).toHaveBeenCalledWith('Une erreur est survenue lors de la création du projet.');
    expect(component.isLoading).toBeFalse();
  }));

  it('should navigate to project details when viewProjectDetails is called', () => {
    spyOn(router, 'navigate');
    component.viewProjectDetails(1);
    expect(router.navigate).toHaveBeenCalledWith(['/project-details/1']);
  });

  it('should unsubscribe on component destruction', () => {
    spyOn(component.subscription, 'unsubscribe');
    component.ngOnDestroy();
    expect(component.subscription.unsubscribe).toHaveBeenCalled();
  });
});
