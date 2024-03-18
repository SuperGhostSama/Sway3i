import { Component } from '@angular/core';

@Component({
  selector: 'app-teacher-demand',
  templateUrl: './teacher-demand.component.html',
  styleUrls: ['./teacher-demand.component.css']
})
export class TeacherDemandComponent {

  createdById: number = 10;
  subject: string = "Mathematics";
  educationLevel: string = "HIGH_SCHOOL";
  description: string = "Looking for a teacher for advanced math classes";

  showForm: boolean = false;

  toggleForm() {
    this.showForm = !this.showForm;
  }

  
}
