import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CourseWithDetailsResponseDTO } from 'src/app/dto/Course/responses/course-with-details-response-DTO';
import { CourseService } from 'src/app/services/Course/course.service';
import { PriceTransferServiceService } from 'src/app/services/PriceTransferService/price-transfer-service.service';

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
    private courseService: CourseService,
    private priceTransferService: PriceTransferServiceService,
    private router: Router
    ) {}

  ngOnInit(): void {
    window.scrollTo({ top: 0, behavior: 'smooth' });
    
    this.fetchCourseDetails();
  }
  
  fetchCourseDetails(): void {
    const courseIdParam = this.route.snapshot.paramMap.get('id');
    if (courseIdParam !== null) {
      this.courseId = +courseIdParam;

      this.courseService.getCourseById(Number(this.courseId)).subscribe(
        (course: CourseWithDetailsResponseDTO) => {
          this.course = course;
        },
        (error) => {
          console.error('Error fetching course details:', error);
        }
      );
    }
  }

  sendPrice(): void {
    this.priceTransferService.setPriceAndType(this.course.price, this.course.type);
  }
  

  subscribeAndSendPrice() {
    this.sendPrice();
    
    this.router.navigate(['/courses-pricing']);
  }
}
