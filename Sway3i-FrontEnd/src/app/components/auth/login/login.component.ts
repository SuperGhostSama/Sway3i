import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NotificationService } from 'src/app/services/Notification/notification.service';
import { AuthService } from 'src/app/services/auth/auth.service'; 

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginRequest = { email: '', password: '' };

  constructor(
    private authService: AuthService, 
    private router: Router,
    private notificationService: NotificationService
    ) {}

  onSubmit() {
    this.authService.authenticate(this.loginRequest).subscribe(
      (response) => {
        console.log('Login response:', response);

        localStorage.setItem('token', response.token);
        localStorage.setItem('refreshToken', response.refreshToken);
        localStorage.setItem('id', response.userId);
        localStorage.setItem('firstName', response.firstName);
        localStorage.setItem('lastName', response.lastName);
        localStorage.setItem('city', response.city);
        localStorage.setItem('email', response.email);
        localStorage.setItem('role', response.role.name);
        localStorage.setItem('isValid', response.isValid);

        this.router.navigate(['/']);
        this.notificationService.show(['You have been successfully logged in'], 'success');

      },
      (error) => {

        this.notificationService.show([error.error.message], 'error');
        
      }
    );
  }
}
