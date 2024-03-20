import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { CourseWithDetailsResponseDTO } from 'src/app/dto/Course/responses/course-with-details-response-DTO';
import { CourseService } from 'src/app/services/Course/course.service';

@Component({
  selector: 'app-about-course-page',
  templateUrl: './about-course-page.component.html',
  styleUrls: ['./about-course-page.component.css']
})
export class AboutCoursePageComponent {

  courseId!: Number;
  course!: CourseWithDetailsResponseDTO;

  constructor(
    private route: ActivatedRoute,
    private courseService: CourseService
    ) {}

  ngOnInit(): void {

    this.fetchCourseDetails();
  }
  
  fetchCourseDetails(): void {
    const courseIdParam = this.route.snapshot.paramMap.get('id');
    if (courseIdParam !== null) {
      this.courseId = +courseIdParam;
      console.log('Course ID:', this.courseId);

      this.courseService.getCourseById(Number(this.courseId)).subscribe(
        (course: CourseWithDetailsResponseDTO) => {
          this.course = course;
          console.log('Course Details:', this.course);
        },
        (error) => {
          console.error('Error fetching course details:', error);
        }
      );
    }
  }
}
