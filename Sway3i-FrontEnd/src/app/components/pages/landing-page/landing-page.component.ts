import { Component } from '@angular/core';
import { CourseWithDetailsResponseDTO } from 'src/app/dto/Course/responses/course-with-details-response-DTO';
import { CourseService } from 'src/app/services/Course/course.service';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent {

  latestCourses: CourseWithDetailsResponseDTO[] = [];

  constructor(
    private courseService: CourseService
    ) { }

    
  ngOnInit(): void {
    window.scrollTo({ top: 0, behavior: 'smooth' });
    this.getAllLatestCourses();
  }

  getAllLatestCourses(): void {
    this.courseService.getAllLatestCourses().subscribe(
      (courses: CourseWithDetailsResponseDTO[]) => {
        this.latestCourses = courses;
        console.log('Latest Courses:', this.latestCourses);
      },
      (error) => {
        console.error('Error fetching latest courses:', error);
      }
    );
  }


}
