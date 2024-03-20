import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PriceTransferServiceService {

  private priceSubject: BehaviorSubject<number> = new BehaviorSubject<number>(0);

  constructor() { }

  setPrice(price: number): void {
    this.priceSubject.next(price);
  }

  getPrice(): Observable<number> {
    return this.priceSubject.asObservable();
  }
  
}
