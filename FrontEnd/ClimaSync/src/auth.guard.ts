// auth.guard.ts

import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';

@Injectable({
  providedIn: 'root', // Angular 6+ syntax for providing services
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    // Implement your authentication logic here
    const isAuthenticated = localStorage.getItem('token'); /* Your authentication logic, e.g., checking for a token */

    if (isAuthenticated) {
      return true; // Allow access to the route
    } else {
      this.router.navigate(['/login']); // Redirect to the login page or another route
      return false; // Prevent access to the route
    }
  }
}
