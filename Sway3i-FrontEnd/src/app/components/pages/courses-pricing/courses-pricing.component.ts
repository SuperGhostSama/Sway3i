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
  discountedPrices: any = {};

  constructor(
    private router: Router,
    private priceTransferService: PriceTransferServiceService,
    private coursePricingService: CoursePricingService
    ) { }

 
  ngOnInit(): void {
    window.scrollTo({ top: 0, behavior: 'smooth' });

    
    this.priceTransferService.getPrice().subscribe(price => {
      this.receivedPrice = price;
    });

    this.priceTransferService.getCourseType().subscribe(courseType => {
      this.receivedCourseType = courseType;
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

    
    this.coursePricingService.calculateDiscountedPrices(request).subscribe(
      (response: any) => {
        
        this.discountedPrices = response.discountedPrices;
      },
      (error) => {
        console.error('Error calculating discounted price:', error);
      }
    );
  }

}
