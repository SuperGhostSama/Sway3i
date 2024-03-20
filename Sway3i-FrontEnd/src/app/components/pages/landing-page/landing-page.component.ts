  import { Component } from '@angular/core';
import { CourseWithDetailsResponseDTO } from 'src/app/dto/Course/responses/course-with-details-response-DTO';
import { Course } from 'src/app/modal/entities/course';
import { CourseService } from 'src/app/services/Course/course.service';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent {

  latestCourses: CourseWithDetailsResponseDTO[] = [];
  filteredCourses: CourseWithDetailsResponseDTO[] = [];


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


  filterCourses(educationLevel: string): void {
    this.filteredCourses = this.latestCourses.filter(course => course.educationLevel === educationLevel);
  }
}
