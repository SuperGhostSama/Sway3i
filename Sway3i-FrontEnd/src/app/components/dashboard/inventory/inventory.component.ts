import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EnrolledCourseResponseDTO } from 'src/app/dto/StudentInCourse/responses/enrolled-course-response-DTO';
import { StudentsInCourseService } from 'src/app/services/StudentInCourse/student-in-course.service';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent {

  enrolledCourses: EnrolledCourseResponseDTO[] = [];

  constructor(
    private router: Router,
    private studentsInCourseService: StudentsInCourseService
  ) { }

  ngOnInit(): void {
    const studentId = Number(localStorage.getItem('id'));

    if (!isNaN(studentId)) {

      this.studentsInCourseService.getEnrolledCoursesByStudentId(studentId)
        .subscribe(
          (courses: EnrolledCourseResponseDTO[]) => {
            this.enrolledCourses = courses;
            console.log(this.enrolledCourses);
          },
          (error) => {
            console.error('Error fetching enrolled courses:', error);
          }
        );
    } else {
      this.router.navigate(['/login']);
    }
  }


}
