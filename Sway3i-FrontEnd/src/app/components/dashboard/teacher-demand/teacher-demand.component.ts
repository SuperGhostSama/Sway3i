import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TeacherDemandRequestDTO } from 'src/app/dto/TeacherDemand/requests/teacher-demand-request-DTO';
import { TeacherDemandResponseDTO } from 'src/app/dto/TeacherDemand/responses/teacher-demand-response-DTO';
import { NotificationService } from 'src/app/services/Notification/notification.service';
import { TeacherDemandService } from 'src/app/services/TeacherDemand/teacher-demand.service';

@Component({
  selector: 'app-teacher-demand',
  templateUrl: './teacher-demand.component.html',
  styleUrls: ['./teacher-demand.component.css']
})
export class TeacherDemandComponent implements OnInit{

  teacherDemands: TeacherDemandResponseDTO[] = [];
  createdById: number | undefined;
  subject: string = '';
  educationLevel: string = '';
  description: string = '';

  showForm: boolean = false;

  constructor(
    private teacherDemandService: TeacherDemandService, 
    private router: Router,
    private notificationService: NotificationService
  ) { }

  ngOnInit(): void {
    // Retrieve email from localStorage
    const email = localStorage.getItem('email');
    if (email) {
      // Call the method to fetch teacher demands using the retrieved email
      this.getTeacherDemandsByEmail(email);
    } else {
      console.error('Email not found in localStorage.');
    }
  }

  toggleForm() {
    this.showForm = !this.showForm;
  }

  onSubmit(): void {
    const teacherDemandRequest: any = {
      createdById: localStorage.getItem('id'),
      subject: this.subject,
      educationLevel: this.educationLevel,
      description: this.description
    };

    // console.log('Teacher demand request:', teacherDemandRequest);

    this.teacherDemandService.createTeacherDemand(teacherDemandRequest).subscribe(
      (response: TeacherDemandResponseDTO) => {
        console.log('Teacher demand created:', response);

        
        this.subject = '';
        this.educationLevel = '';
        this.description = '';

        // Hide the form
        this.showForm = false;

        this.ngOnInit();
        this.notificationService.show(['Teacher Demand successfully created'], 'success');
      },
      (error) => {
        // console.error('Error creating teacher demand:', error);

        this.notificationService.show([error.error.message], 'error');
      }
    );
  }
  

  getTeacherDemandsByEmail(email: string): void {
    this.teacherDemandService.getTeacherDemandsByEmail(email).subscribe(
      (teacherDemands: TeacherDemandResponseDTO[]) => {
        this.teacherDemands = teacherDemands;
        console.log('Teacher demands:', teacherDemands);
      },
      (error) => {
        console.error('Error fetching teacher demands:', error);
      }
    );
  }

  isDemandAccepted(): boolean {
    return this.teacherDemands.some(demand => demand.status.toString() === 'ACCEPTED');
  }
  

}
