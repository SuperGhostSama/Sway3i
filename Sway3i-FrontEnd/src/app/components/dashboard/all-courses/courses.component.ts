import { Component, OnInit } from '@angular/core';
import { CourseResponseDTO } from 'src/app/dto/Course/responses/course-reponse-DTO';
import { CourseWithDetailsResponseDTO } from 'src/app/dto/Course/responses/course-with-details-response-DTO';
import { CourseService } from 'src/app/services/Course/course.service';
import { NotificationService } from 'src/app/services/Notification/notification.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit{

  
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

  confirmDelete(course: CourseWithDetailsResponseDTO): void {
    const confirmDelete = window.confirm('Are you sure you want to delete this course?');

    if (confirmDelete) {
        this.deleteCourse(course.createdByUser.id);
    }
}

  deleteCourse(courseId: number): void {
      this.courseService.deleteCourse(courseId).subscribe(
          () => {
              // Refresh the course list after deletion
              this.getAllCourses();
              this.notificationService.show(['Course successfully deleted'], 'success');
          },
          (error) => {
              console.error('Error deleting course:', error);
              this.notificationService.show([error.error.message], 'error');
          }
      );
  }
}
