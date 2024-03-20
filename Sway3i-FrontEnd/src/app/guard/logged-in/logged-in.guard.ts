import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const loggedInGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const jwtToken = localStorage.getItem('token');

  if (!jwtToken) {
    return true;
  } else {

    router.navigate(['/']); 
    return false;
  }
};
