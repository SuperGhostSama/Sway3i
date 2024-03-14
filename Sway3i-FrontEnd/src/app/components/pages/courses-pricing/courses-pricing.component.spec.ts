import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoursesPricingComponent } from './courses-pricing.component';

describe('CoursesPricingComponent', () => {
  let component: CoursesPricingComponent;
  let fixture: ComponentFixture<CoursesPricingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CoursesPricingComponent]
    });
    fixture = TestBed.createComponent(CoursesPricingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
