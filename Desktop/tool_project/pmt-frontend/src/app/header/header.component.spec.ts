import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router'; // Importer ActivatedRoute
import { HeaderComponent } from './header.component';
import { AuthService } from '../services/auth.service'; // Importer le service d'authentification
import { of } from 'rxjs'; // Importer 'of' pour créer un Observable

class MockAuthService {
  checkAuthStatus = jasmine.createSpy('checkAuthStatus').and.returnValue(false); // Simuler le statut d'authentification
  logout = jasmine.createSpy('logout'); // Simuler la méthode logout
}

describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;
  let mockAuthService: MockAuthService;

  beforeEach(async () => {
    mockAuthService = new MockAuthService();

    await TestBed.configureTestingModule({
      imports: [
        HeaderComponent,
        HttpClientTestingModule
      ],
      providers: [
        {
          provide: ActivatedRoute, // Fournir un faux ActivatedRoute
          useValue: {
            params: of({}), // Simuler les paramètres de route
            snapshot: { params: {} } // Simuler un snapshot de route
          }
        },
        { provide: AuthService, useValue: mockAuthService } // Fournir le service d'authentification simulé
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should return false when user is not authenticated', () => {
    const isAuthenticated = component.isAuthenticated();
    expect(isAuthenticated).toBe(false);
    expect(mockAuthService.checkAuthStatus).toHaveBeenCalled();
  });

  it('should return true when user is authenticated', () => {
    mockAuthService.checkAuthStatus.and.returnValue(true); // Simuler l'utilisateur comme authentifié
    const isAuthenticated = component.isAuthenticated();
    expect(isAuthenticated).toBe(true);
    expect(mockAuthService.checkAuthStatus).toHaveBeenCalled();
  });

  it('should call logout and navigate to /connexion', () => {
    const routerSpy = spyOn((component as any).router, 'navigate'); // Espionner la méthode navigate du router
    component.logout();
    expect(mockAuthService.logout).toHaveBeenCalled(); // Vérifier que la méthode logout a été appelée
    expect(routerSpy).toHaveBeenCalledWith(['/connexion']); // Vérifier que la navigation a été appelée
  });
});
