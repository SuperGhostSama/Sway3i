import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AboutCoursePageComponent } from './about-course-page.component';

describe('AboutCoursePageComponent', () => {
  let component: AboutCoursePageComponent;
  let fixture: ComponentFixture<AboutCoursePageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AboutCoursePageComponent]
    });
    fixture = TestBed.createComponent(AboutCoursePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
