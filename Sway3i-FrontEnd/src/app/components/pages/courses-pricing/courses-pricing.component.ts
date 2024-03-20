import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CalculateDiscountedPriceRequestDTO } from 'src/app/dto/CoursePricing/requests/calculate-discounted-price-request-DTO';
import { CalculateDiscountedPriceResponseDTO } from 'src/app/dto/CoursePricing/responses/calculate-discounted-price-response-DTO';
import { CoursePricingService } from 'src/app/services/CoursePricing/course-pricing.service';
import { PriceTransferServiceService } from 'src/app/services/PriceTransferService/price-transfer-service.service';

@Component({
  selector: 'app-courses-pricing',
  templateUrl: './courses-pricing.component.html',
  styleUrls: ['./courses-pricing.component.css']
})
export class CoursesPricingComponent {

  receivedPrice: number = 0;
  receivedCourseType: string = '';

  constructor(
    private router: Router,
    private priceTransferService: PriceTransferServiceService,
    private coursePricingService: CoursePricingService
    ) { }

 
  ngOnInit(): void {
    this.priceTransferService.getPrice().subscribe(price => {
      this.receivedPrice = price;
      console.log('Received Price:', this.receivedPrice);
    });

    this.priceTransferService.getCourseType().subscribe(courseType => {
      this.receivedCourseType = courseType;
      console.log('Received Course Type:', this.receivedCourseType);
    });

    if (this.receivedPrice === 0) {
      this.router.navigate(['/courses-list']);
    }


    this.getDiscountedPrice();
  }

  getDiscountedPrice(): void {
    const request: any = {
      coursePrice: this.receivedPrice,
      courseType: this.receivedCourseType
    };

    console.log('Request:', request);
    
    this.coursePricingService.calculateDiscountedPrices(request).subscribe(
      (response: any) => {
        console.log('Discounted Price:', response.discountedPrices);
        this.receivedPrice = response.discountedPrices;
      },
      (error) => {
        console.error('Error calculating discounted price:', error);
      }
    );
  }

}
