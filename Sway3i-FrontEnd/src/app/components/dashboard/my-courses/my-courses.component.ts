import { Component } from '@angular/core';
import { CourseRequestDTO } from 'src/app/dto/Course/requests/course-request-DTO';
import { CourseResponseDTO } from 'src/app/dto/Course/responses/course-reponse-DTO';
import { CourseWithDetailsResponseDTO } from 'src/app/dto/Course/responses/course-with-details-response-DTO';
import { ProgramResponseDTO } from 'src/app/dto/Program/responses/program-response-DTO';
import { ProgramDTO } from 'src/app/dto/Utils/program-DTO';
import { CourseType } from 'src/app/modal/enums/course-type-enum';
import { EducationLevel } from 'src/app/modal/enums/education-level-enum';
import { CourseService } from 'src/app/services/Course/course.service';
import { NotificationService } from 'src/app/services/Notification/notification.service';
import { ProgramService } from 'src/app/services/Program/program.service';

@Component({
  selector: 'app-my-courses',
  templateUrl: './my-courses.component.html',
  styleUrls: ['./my-courses.component.css']
})
export class MyCoursesComponent {

  createdByUserId!: number;
  firstName!: string;
  lastName!: string;
  subject!: string;
  courseDetails!: string;
  courseIsFor!: string;
  price!: number;
  city!: string;
  educationLevel!: EducationLevel;
  type!: CourseType;
  maxStudents!: number;
  programIds: number[] = [];
  
  allPrograms!: ProgramResponseDTO[];

  constructor(
    private courseService: CourseService,
    private notificationService: NotificationService,
    private programService: ProgramService
    ) { }

  allCourses: CourseWithDetailsResponseDTO[] = [];
  
  courses: any[] = [];

  showForm: boolean = false;

  ngOnInit(): void {
    const email = localStorage.getItem('email');
    if (email) {
      this.getAllCoursesByEmail(email);
      this.getAllPrograms();
    }



  }

  toggleForm() {
    this.showForm = !this.showForm;
  }

  getAllPrograms(): void {
    this.programService.getAllPrograms().subscribe(
      (programs: ProgramResponseDTO[]) => {
        this.allPrograms = programs;
      },
      (error) => {
        console.error('Error fetching programs:', error);
      }
    );
  }

  getAllCoursesByEmail(email: string): void {
    
    this.courseService.getAllCoursesByEmail(email).subscribe(
      (courses: CourseWithDetailsResponseDTO[]) => {
        this.allCourses = courses;
        console.log('Courses:', this.allCourses);
        
      },
      (error) => {
        console.error('Error fetching courses:', error);
      }
    );
  }

  deleteCourse(id: number): void {
    this.courseService.deleteCourse(id).subscribe(
      () => {
        this.getAllCoursesByEmail(localStorage.getItem('email')!);
      },
      (error) => {
        console.error('Error deleting course:', error);
      }
    );
  }



  onSubmit(): void {
    const courseRequest: CourseRequestDTO = {
      createdByUserId: localStorage.getItem('id') as unknown as number,
      subject: this.subject,
      courseDetails: this.courseDetails,
      courseIsFor: this.courseIsFor,
      price: this.price,
      city: this.city,
      educationLevel: this.educationLevel as EducationLevel, 
      type: this.type,
      maxStudents: this.maxStudents,
      programIds: this.programIds 
    };
  
      
    this.courseService.createCourse(courseRequest).subscribe(
      (response) => {
  
        // Hide the form
        this.showForm = false;
        
        // Fetch all courses
        this.ngOnInit();
  
        this.notificationService.show(['Course added successfully'], 'success');
      },
      (error) => {
        this.notificationService.show([error.error.message], 'error');
      }
    );
  }

  toggleProgram(programId: number): void {
    const index = this.programIds.indexOf(programId);
    if (index === -1) {
        // If the programId is not in the array, add it
        this.programIds.push(programId);
    } else {
        // If the programId is already in the array, remove it
        this.programIds.splice(index, 1);
    }
}
}

