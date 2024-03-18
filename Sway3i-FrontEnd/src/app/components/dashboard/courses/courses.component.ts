import { Component } from '@angular/core';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent {

  constructor() { }

  createdByUserId: number = 1;
  subject: string = '';
  courseDetails: string = '';
  courseIsFor: string = '';
  price: number = 0;
  city: string = '';
  educationLevel: string = '';
  type: string = '';
  maxStudents: number = 0;
  programs: string = '';


  showForm: boolean = false;

  toggleForm() {
    this.showForm = !this.showForm;
  }


  
}
