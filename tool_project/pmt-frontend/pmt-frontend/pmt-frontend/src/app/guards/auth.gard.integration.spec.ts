import { TestBed, fakeAsync, tick } from '@angular/core/testing';
import { Router, RouterModule } from '@angular/router';
import { Routes, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Location } from '@angular/common';
import { AutGuardService } from './auth.guard';
import { Component } from '@angular/core';

// Composants factices pour les routes
@Component({ template: `<h1>Public</h1>` })
class PublicComponent {}

@Component({ template: `<h1>Admin</h1>` })
class AdminComponent {}

@Component({ template: `<h1>User</h1>` })
class UserComponent {}

// Configurer les routes pour le test d'intégration
const routes: Routes = [
  { path: 'public', component: PublicComponent },
  { path: 'admin', component: AdminComponent, canActivate: [AutGuardService], data: { role: 'admin' } },
  { path: 'user', component: UserComponent, canActivate: [AutGuardService], data: { role: 'user' } },
];

describe('AutGuardService (integration test)', () => {
  let router: Router;
  let location: Location;
  let authGuard: AutGuardService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterModule.forRoot(routes)],
      providers: [AutGuardService],
      declarations: [PublicComponent, AdminComponent, UserComponent]
    }).compileComponents();

    router = TestBed.inject(Router);
    location = TestBed.inject(Location);
    authGuard = TestBed.inject(AutGuardService);
    router.initialNavigation();
  });

  it('should allow navigation to public route without authentication', fakeAsync(() => {
    router.navigate(['public']);
    tick();
    expect(location.path()).toBe('/public');
  }));

  it('should deny access to admin route if not logged in', fakeAsync(() => {
    authGuard.setLoggedIn(false);
    router.navigate(['admin']);
    tick();
    expect(location.path()).not.toBe('/admin');
  }));

  it('should allow access to admin route for logged in admin', fakeAsync(() => {
    authGuard.setLoggedIn(true, ['admin']);
    router.navigate(['admin']);
    tick();
    expect(location.path()).toBe('/admin');
  }));

  it('should deny access to admin route for logged in non-admin user', fakeAsync(() => {
    authGuard.setLoggedIn(true, ['user']);
    router.navigate(['admin']);
    tick();
    expect(location.path()).not.toBe('/admin');
  }));

  it('should allow access to user route for logged in user', fakeAsync(() => {
    authGuard.setLoggedIn(true, ['user']);
    router.navigate(['user']);
    tick();
    expect(location.path()).toBe('/user');
  }));

  it('should deny access to user route for non-logged in user', fakeAsync(() => {
    authGuard.setLoggedIn(false);
    router.navigate(['user']);
    tick();
    expect(location.path()).not.toBe('/user');
  }));
});
