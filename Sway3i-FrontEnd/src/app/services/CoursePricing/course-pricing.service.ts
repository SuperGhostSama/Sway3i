import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CalculateDiscountedPriceRequestDTO } from 'src/app/dto/CoursePricing/requests/calculate-discounted-price-request-DTO';
import { CalculateDiscountedPriceResponseDTO } from 'src/app/dto/CoursePricing/responses/calculate-discounted-price-response-DTO';

@Injectable({
  providedIn: 'root'
})
export class CoursePricingService {
  private apiUrl = `${environment.api}course-pricing`;

  constructor(private http: HttpClient) { }

  calculateDiscountedPrices(request: CalculateDiscountedPriceRequestDTO): Observable<CalculateDiscountedPriceResponseDTO> {
    return this.http.post<CalculateDiscountedPriceResponseDTO>(`${this.apiUrl}/calculate-discounted-prices`, request);
  }
  
}
