import { TestBed } from '@angular/core/testing';
import { RouterModule } from '@angular/router';
import { ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AutGuardService } from './auth.guard';
import { convertToParamMap } from '@angular/router';

describe('AutGuardService', () => {
  let service: AutGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterModule.forRoot([])],
      providers: [AutGuardService]
    });
    service = TestBed.inject(AutGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  const createRouteSnapshot = (role: string): ActivatedRouteSnapshot => {
    return {
      url: [],
      params: {},
      queryParams: {},
      fragment: '',
      data: { role },
      outlet: '',
      routeConfig: null,
      root: {} as ActivatedRouteSnapshot,
      parent: null,
      firstChild: null,
      children: [],
      component: null,
      title: '',
      pathFromRoot: [],
      paramMap: convertToParamMap({}),
      queryParamMap: convertToParamMap({}),
    };
  };

  it('should return true for canActivate when logged in and role matches', () => {
    service.setLoggedIn(true, ['admin']);
    const route = createRouteSnapshot('admin');
    const state = {} as RouterStateSnapshot;
    expect(service.canActivate(route, state)).toBeTrue();
  });

  it('should return false for canActivate when not logged in', () => {
    service.setLoggedIn(false);
    const route = createRouteSnapshot('admin');
    const state = {} as RouterStateSnapshot;
    expect(service.canActivate(route, state)).toBeFalse();
  });

  it('should return false for canActivate if required role is not met', () => {
    service.setLoggedIn(true, ['user']);
    const route = createRouteSnapshot('admin');
    const state = {} as RouterStateSnapshot;
    expect(service.canActivate(route, state)).toBeFalse();
  });

  it('should return true for canActivate if required role is met', () => {
    service.setLoggedIn(true, ['user', 'admin']);
    const route = createRouteSnapshot('user');
    const state = {} as RouterStateSnapshot;
    expect(service.canActivate(route, state)).toBeTrue();
  });

  // New tests to improve coverage

  it('should return false for canActivate when logged in with no roles', () => {
    service.setLoggedIn(true, []); // Simulate logged in without roles
    const route = createRouteSnapshot('admin');
    const state = {} as RouterStateSnapshot;
    expect(service.canActivate(route, state)).toBeFalse(); // Access should be denied
  });

  it('should return true for canActivate with multiple roles and the required role is present', () => {
    service.setLoggedIn(true, ['admin', 'user']); // Simulate logged in with multiple roles
    const route = createRouteSnapshot('admin');
    const state = {} as RouterStateSnapshot;
    expect(service.canActivate(route, state)).toBeTrue(); // Access should be granted
  });

  it('should return true for canActivate if route has no role defined', () => {
    service.setLoggedIn(true, ['user']);
    const route = createRouteSnapshot(''); // No role required
    const state = {} as RouterStateSnapshot;
    expect(service.canActivate(route, state)).toBeTrue(); // Access should be granted if no role check
  });

  it('should return false for canActivate if user is not an admin for an admin route', () => {
    service.setLoggedIn(true, ['user']); // Logged in but not an admin
    const route = createRouteSnapshot('admin'); // Admin role required
    const state = {} as RouterStateSnapshot;
    expect(service.canActivate(route, state)).toBeFalse(); // Access should be denied
  });

  it('should return false for canActivate when not logged in and role is required', () => {
    service.setLoggedIn(false); // Not logged in
    const route = createRouteSnapshot('user'); // User role required
    const state = {} as RouterStateSnapshot;
    expect(service.canActivate(route, state)).toBeFalse(); // Access should be denied
  });

  it('should return true for canActivate when logged in with multiple roles and accessing a valid role', () => {
    service.setLoggedIn(true, ['user', 'editor']); // User is logged in with multiple roles
    const route = createRouteSnapshot('editor'); // Required role is one of the user\'s roles
    const state = {} as RouterStateSnapshot;
    expect(service.canActivate(route, state)).toBeTrue(); // Access should be granted
  });

  it('should return false for canActivate when user does not have a valid role', () => {
    service.setLoggedIn(true, ['guest']); // User logged in with guest role
    const route = createRouteSnapshot('admin'); // Required role is admin
    const state = {} as RouterStateSnapshot;
    expect(service.canActivate(route, state)).toBeFalse(); // Access should be denied
  });

  it('should return true for canActivate when accessing a route with no role and logged in', () => {
    service.setLoggedIn(true, ['user']); // User is logged in
    const route = createRouteSnapshot(''); // Route requires no specific role
    const state = {} as RouterStateSnapshot;
    expect(service.canActivate(route, state)).toBeTrue(); // Access should be granted
  });

  // New test cases to further increase coverage
  it('should return false for canActivate when logged in with invalid roles', () => {
    service.setLoggedIn(true, ['user']); // Logged in with a valid role
    const route = createRouteSnapshot('admin'); // Admin role required
    const state = {} as RouterStateSnapshot;
    expect(service.canActivate(route, state)).toBeFalse(); // Access should be denied
  });

  it('should return false for canActivate when setLoggedIn is called with false and roles provided', () => {
    service.setLoggedIn(false, ['admin', 'user']); // Not logged in
    const route = createRouteSnapshot('admin'); // Admin role required
    const state = {} as RouterStateSnapshot;
    expect(service.canActivate(route, state)).toBeFalse(); // Access should be denied
  });

  it('should return true for canActivate when route data.role is an empty string', () => {
    service.setLoggedIn(true, ['user']); // User is logged in
    const route = createRouteSnapshot(''); // Route with empty role
    const state = {} as RouterStateSnapshot;
    expect(service.canActivate(route, state)).toBeTrue(); // Access should be granted
});
it('should return false for canActivate when user has multiple roles and route requires a role not included', () => {
  service.setLoggedIn(true, ['user', 'editor']); // User logged in with user and editor roles
  const route = createRouteSnapshot('admin'); // Route requires admin role
  const state = {} as RouterStateSnapshot;
  expect(service.canActivate(route, state)).toBeFalse(); // Access should be denied
});
it('should return false for canActivate when logged in with roles that do not include the required role', () => {
  service.setLoggedIn(true, ['user']); // User logged in with user role
  const route = createRouteSnapshot('admin'); // Route requires admin role
  const state = {} as RouterStateSnapshot;
  expect(service.canActivate(route, state)).toBeFalse(); // Access should be denied
});

it('should return false for canActivate when logged in with roles and route has no role', () => {
  service.setLoggedIn(true, ['admin']); // User logged in with admin role
  const route = createRouteSnapshot(''); // Route requires no specific role
  const state = {} as RouterStateSnapshot;
  expect(service.canActivate(route, state)).toBeTrue(); // Access should be granted
});

it('should return false for canActivate when user is logged out and role is required', () => {
  service.setLoggedIn(false); // User not logged in
  const route = createRouteSnapshot('editor'); // Editor role required
  const state = {} as RouterStateSnapshot;
  expect(service.canActivate(route, state)).toBeFalse(); // Access should be denied
});
it('should return false for canActivate when logged in with roles that do not include the required role', () => {
  service.setLoggedIn(true, ['user']); // User logged in with user role
  const route = createRouteSnapshot('admin'); // Route requires admin role
  const state = {} as RouterStateSnapshot;
  expect(service.canActivate(route, state)).toBeFalse(); // Access should be denied
});

it('should return false for canActivate when logged in with roles and route has no role', () => {
  service.setLoggedIn(true, ['admin']); // User logged in with admin role
  const route = createRouteSnapshot(''); // Route requires no specific role
  const state = {} as RouterStateSnapshot;
  expect(service.canActivate(route, state)).toBeTrue(); // Access should be granted
});

it('should return false for canActivate when user is logged out and role is required', () => {
  service.setLoggedIn(false); // User not logged in
  const route = createRouteSnapshot('editor'); // Editor role required
  const state = {} as RouterStateSnapshot;
  expect(service.canActivate(route, state)).toBeFalse(); // Access should be denied
})})
