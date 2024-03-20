import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PriceTransferServiceService } from 'src/app/services/PriceTransferService/price-transfer-service.service';

@Component({
  selector: 'app-courses-pricing',
  templateUrl: './courses-pricing.component.html',
  styleUrls: ['./courses-pricing.component.css']
})
export class CoursesPricingComponent {

  receivedPrice: number = 0;

  constructor(
    private router: Router,
    private priceTransferService: PriceTransferServiceService
    ) { }

  ngOnInit(): void {

    this.priceTransferService.getPrice().subscribe(price => {
      this.receivedPrice = price;
      console.log('Received Price:', this.receivedPrice);
    });

    if (this.receivedPrice === 0) {
      this.router.navigate(['/courses-list']);
    }
  }

}
