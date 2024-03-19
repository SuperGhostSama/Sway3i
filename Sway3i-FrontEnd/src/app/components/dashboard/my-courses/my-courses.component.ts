import { Component } from '@angular/core';
import { CourseResponseDTO } from 'src/app/dto/Course/responses/course-reponse-DTO';
import { CourseWithDetailsResponseDTO } from 'src/app/dto/Course/responses/course-with-details-response-DTO';
import { CourseService } from 'src/app/services/Course/course.service';

@Component({
  selector: 'app-my-courses',
  templateUrl: './my-courses.component.html',
  styleUrls: ['./my-courses.component.css']
})
export class MyCoursesComponent {

  constructor(private courseService: CourseService) { }

  courses: CourseWithDetailsResponseDTO[] = [];
  showForm: boolean = false;

  ngOnInit(): void {
    const email = localStorage.getItem('email');
    if (email) {
      this.getAllCoursesByEmail(email);
    }
  }

  toggleForm() {
    this.showForm = !this.showForm;
  }

  getAllCoursesByEmail(email: string): void {
    
    this.courseService.getAllCoursesByEmail(email).subscribe(
      (courses: CourseWithDetailsResponseDTO[]) => {
        this.courses = courses;
        console.log('Courses:', this.courses);
        
      },
      (error) => {
        console.error('Error fetching courses:', error);
      }
    );
  }
}
