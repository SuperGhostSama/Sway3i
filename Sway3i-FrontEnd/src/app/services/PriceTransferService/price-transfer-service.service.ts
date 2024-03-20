import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PriceTransferServiceService {

  private priceSubject: BehaviorSubject<number> = new BehaviorSubject<number>(0);
  private courseTypeSubject: BehaviorSubject<string> = new BehaviorSubject<string>('');

  constructor() { }

  setPriceAndType(price: number, courseType: string): void {
    this.priceSubject.next(price);
    this.courseTypeSubject.next(courseType);
  }

  getPrice(): Observable<number> {
    return this.priceSubject.asObservable();
  }

  getCourseType(): Observable<string> {
    return this.courseTypeSubject.asObservable();
  }



}
