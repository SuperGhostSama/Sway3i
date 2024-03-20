import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const teacherGuard: CanActivateFn = (route, state) => {

  const router = inject(Router);

  const role = localStorage.getItem('role');
  if (role !== 'ROLE_TEACHER') {

    router.navigate(['/']);

    return false;
  }
  return true;
  
};
