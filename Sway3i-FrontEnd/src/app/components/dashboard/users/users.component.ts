import { Component } from '@angular/core';
import { CourseResponseDTO } from 'src/app/dto/Course/responses/course-reponse-DTO';
import { CourseWithDetailsResponseDTO } from 'src/app/dto/Course/responses/course-with-details-response-DTO';
import { StudentsInCourseRequestDTO } from 'src/app/dto/StudentInCourse/requests/student-in-course-request-DTO';
import { StudentsInCourseResponseDTO } from 'src/app/dto/StudentInCourse/responses/student-in-course-response-DTO';
import { Course } from 'src/app/modal/entities/course';
import { User } from 'src/app/modal/entities/user';
import { CourseService } from 'src/app/services/Course/course.service';
import { NotificationService } from 'src/app/services/Notification/notification.service';
import { StudentsInCourseService } from 'src/app/services/StudentInCourse/student-in-course.service';
import { UsersService } from 'src/app/services/Users/users.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent {

  students: User[] = [];
  courses: CourseWithDetailsResponseDTO[] = [];

  selectedStudentId!: number;
  selectedCourseId!: number;
  selectedPricingPlan!: string;

  constructor(
    private studentService: UsersService,
    private courseService: CourseService,
    private studentInCourseService: StudentsInCourseService,
    private notificationService: NotificationService
    ) { }

  ngOnInit(): void {
    this.loadStudents();
    this.loadCourses();
  }

  loadStudents(): void {
    this.studentService.getAllStudents().subscribe(
      (students: User[]) => {
        this.students = students;
      },
      (error) => {
        console.error('Error fetching students:', error);
      }
    );
  }

  loadCourses(): void {
    this.courseService.getAllCourses().subscribe(
      (courses: CourseWithDetailsResponseDTO[]) => {
        this.courses = courses;
      },
      (error) => {
        console.error('Error fetching courses:', error);
      }
    );
  }

  onSubmit(): void {

    const studentsInCourseRequest: any = {
      studentId: this.selectedStudentId,
      courseId: this.selectedCourseId,
      pricingPlan: this.selectedPricingPlan
    };

    this.studentInCourseService.createStudentsInCourse(studentsInCourseRequest).subscribe(
      (response: StudentsInCourseResponseDTO) => {

        this.notificationService.show(['Students added to course successfully'], 'success');
      },
      (error) => {
        this.notificationService.show([error.error.message], 'error');
      }
    );
  }
}
