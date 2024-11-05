import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { AppComponent } from './app.component';
import { AuthService } from './services/auth.service';

describe('AppComponent Integration Test', () => {
  let component: AppComponent;
  let fixture: ComponentFixture<AppComponent>;
  let router: Router;

  beforeEach(async () => {
    // Configuration du TestBed
    await TestBed.configureTestingModule({
      imports: [AppComponent], // Importation du composant autonome
      providers: [
        {
          provide: Router,
          useValue: { navigate: jasmine.createSpy('navigate') }, // Espion pour le router
        },
        {
          provide: AuthService,
          useValue: { logout: jasmine.createSpy('logout'), checkAuthStatus: () => true }, // Espion pour AuthService
        },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
    router = TestBed.inject(Router); // Récupération de l'instance du router
    fixture.detectChanges();
  });

  it('should create the app component', () => {
    expect(component).toBeTruthy(); // Vérifie que le composant est créé
  });

  it('should navigate to /inscription', () => {
    component.goToInscription(); // Appel de la méthode de navigation
    expect(router.navigate).toHaveBeenCalledWith(['/inscription']); // Vérifie que la méthode navigate a été appelée avec le bon argument
  });
});
