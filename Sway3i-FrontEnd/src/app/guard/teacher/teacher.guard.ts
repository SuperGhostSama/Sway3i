import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const teacherGuard: CanActivateFn = (route, state) => {

  const router = inject(Router);

  const role = localStorage.getItem('role');
  const isValid = localStorage.getItem('isValid');

  if (role !== 'ROLE_TEACHER' && isValid !== 'true') {

    router.navigate(['/']);

    return false;
  }
  return true;
  
};
