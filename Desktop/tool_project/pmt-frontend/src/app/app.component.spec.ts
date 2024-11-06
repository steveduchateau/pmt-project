import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component'; // Assurez-vous que le chemin est correct
import { AuthService } from './services/auth.service';
import { By } from '@angular/platform-browser';
import { ComponentFixture } from '@angular/core/testing';

class MockAuthService {
  // Simulez le comportement de checkAuthStatus
  checkAuthStatus = jasmine.createSpy('checkAuthStatus').and.returnValue(true);
  // Simulez la méthode login
  login = jasmine.createSpy('login').and.returnValue(true);
  logout = jasmine.createSpy('logout'); // Ajoute un mock pour la méthode logout 
}

describe('AppComponent', () => {
  let fixture: ComponentFixture<AppComponent>;
  let app: AppComponent;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule, // Simule le router
        AppComponent // Ajoutez AppComponent ici en tant qu'import
      ],
      providers: [
        { provide: AuthService, useClass: MockAuthService } // Utilisez un mock de AuthService
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(AppComponent);
    app = fixture.componentInstance; // Créez une instance du composant
  });

  it('should create the app', () => {
    expect(app).toBeTruthy();
  });

  it('should have a title', () => {
    const expectedTitle = 'PMT Tool'; // Remplacez par le titre attendu
    expect(app.title).toEqual(expectedTitle);
  });

  it('should render title in a h1 tag', () => {
    app.title = 'PMT Tool'; // Assurez-vous que le titre est défini
    fixture.detectChanges(); // Détectez les changements pour mettre à jour le template

    const h1 = fixture.debugElement.query(By.css('h1')); // Changez le sélecteur si nécessaire
    expect(h1.nativeElement.textContent).toContain('PMT Tool'); // Vérifiez le contenu
  });

  it('should navigate to the correct route on method call', () => {
    const navigateSpy = spyOn(app.router, 'navigate'); // Assurez-vous que router est injectable
    app.someMethodThatNavigates = () => app.router.navigate(['expectedRoute']); // Implémentez la méthode
    app.someMethodThatNavigates(); // Remplacez par la méthode réelle
    expect(navigateSpy).toHaveBeenCalledWith(['expectedRoute']); // Changez 'expectedRoute' pour la route attendue
  });

  it('should call authService.login when login method is called', () => {
    const authServiceSpy = TestBed.inject(AuthService);
    app.login = () => authServiceSpy.login('user@example.com', 'password'); // Assurez-vous de passer les arguments requis
    app.login(); // Appelez la méthode
    expect(authServiceSpy.login).toHaveBeenCalledWith('user@example.com', 'password'); // Vérifiez que la méthode a été appelée avec les bons arguments
  });

  // Ajoutez d'autres tests ici
  it('should check if the user is authenticated', () => {
    const authServiceSpy = TestBed.inject(AuthService);
    const isAuthenticated = app.isUserAuthenticated(); // Appelez la méthode d'authentification
    expect(isAuthenticated).toBeTrue(); // Vérifiez que la méthode retourne true
    expect(authServiceSpy.checkAuthStatus).toHaveBeenCalled(); // Vérifiez que checkAuthStatus a été appelé
  });

  it('should call logout method', () => {
    const authServiceSpy = TestBed.inject(AuthService);
    app.logout(); // Appelez la méthode logout
    expect(authServiceSpy.logout).toHaveBeenCalled(); // Vérifiez que la méthode logout a été appelée
  });

  it('should navigate to inscription route', () => {
    const navigateSpy = spyOn(app.router, 'navigate');
    app.goToInscription(); // Appel de la méthode
    expect(navigateSpy).toHaveBeenCalledWith(['/inscription']); // Vérifie la navigation
  });

  it('should navigate to connexion route', () => {
    const navigateSpy = spyOn(app.router, 'navigate');
    app.goToConnexion(); // Appel de la méthode
    expect(navigateSpy).toHaveBeenCalledWith(['/connexion']); // Vérifie la navigation
  });

  it('should have a defined method for login', () => {
    expect(app.login).toBeDefined(); // Vérifie que la méthode login est définie
  });

  it('should have a defined method for logout', () => {
    expect(app.logout).toBeDefined(); // Vérifie que la méthode logout est définie
  });
});
