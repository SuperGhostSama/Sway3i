import { Component } from '@angular/core';

@Component({
  selector: 'app-all-teacher-demands',
  templateUrl: './all-teacher-demands.component.html',
  styleUrls: ['./all-teacher-demands.component.css']
})
export class AllTeacherDemandsComponent {
  createdById: number = 10;
  subject: string = "Mathematics";
  educationLevel: string = "HIGH_SCHOOL";
  description: string = "Looking for a teacher for advanced math classes";
}
