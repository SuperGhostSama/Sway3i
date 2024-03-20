import { Component, OnInit } from '@angular/core';
import { CourseWithDetailsResponseDTO } from 'src/app/dto/Course/responses/course-with-details-response-DTO';
import { CourseService } from 'src/app/services/Course/course.service';
import { NotificationService } from 'src/app/services/Notification/notification.service';

@Component({
  selector: 'app-courses-cards',
  templateUrl: './courses-cards.component.html',
  styleUrls: ['./courses-cards.component.css']
})
export class CoursesCardsComponent implements OnInit{

  courses: CourseWithDetailsResponseDTO[] = [];

  constructor(
    private courseService: CourseService,
    private notificationService: NotificationService
    ) { }

  ngOnInit(): void {
    this.getAllCourses();
  }

  getAllCourses(): void {
    this.courseService.getAllCourses().subscribe(
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
