import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent {
  userRole: string | null = localStorage.getItem('role');
  teacherIsValid: string | null = localStorage.getItem('isValid');
  userFullName : string | null = localStorage.getItem('firstName') + ' ' + localStorage.getItem('lastName');
  constructor(private router: Router) {}

  logout() {
    
    localStorage.clear(); 

    this.router.navigate(['/']);
  }

  
}
