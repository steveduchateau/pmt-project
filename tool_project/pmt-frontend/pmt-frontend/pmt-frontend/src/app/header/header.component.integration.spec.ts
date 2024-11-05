import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HeaderComponent } from './header.component';
import { AuthService } from '../services/auth.service';
import { of } from 'rxjs';
import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({ template: '<p>Connexion Page</p>' })
class ConnexionComponent {}

const routes = [
  { path: 'connexion', component: ConnexionComponent },
  { path: 'home', component: HeaderComponent }
];

class MockAuthService {
  checkAuthStatus = jasmine.createSpy('checkAuthStatus').and.returnValue(false);
  logout = jasmine.createSpy('logout');
}

describe('HeaderComponent (integration test)', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;
  let mockAuthService: MockAuthService;
  let router: Router;
  let location: Location;

  beforeEach(async () => {
    mockAuthService = new MockAuthService();

    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule.withRoutes(routes),
        HeaderComponent // Importer le composant standalone ici
      ],
      declarations: [ConnexionComponent], // Supprimer HeaderComponent des declarations
      providers: [
        { provide: AuthService, useValue: mockAuthService }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    router = TestBed.inject(Router);
    location = TestBed.inject(Location);

    router.initialNavigation();
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should return false when user is not authenticated', () => {
    mockAuthService.checkAuthStatus.and.returnValue(false);
    const isAuthenticated = component.isAuthenticated();
    expect(isAuthenticated).toBe(false);
    expect(mockAuthService.checkAuthStatus).toHaveBeenCalled();
  });

  it('should return true when user is authenticated', () => {
    mockAuthService.checkAuthStatus.and.returnValue(true);
    const isAuthenticated = component.isAuthenticated();
    expect(isAuthenticated).toBe(true);
    expect(mockAuthService.checkAuthStatus).toHaveBeenCalled();
  });

  it('should call logout and navigate to /connexion', fakeAsync(() => {
    component.logout();
    tick();
    expect(mockAuthService.logout).toHaveBeenCalled();
    expect(location.path()).toBe('/connexion');
  }));
});
