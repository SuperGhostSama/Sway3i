import { TestBed } from '@angular/core/testing';

import { StudentInCourseService } from './student-in-course.service';

describe('StudentInCourseService', () => {
  let service: StudentInCourseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StudentInCourseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
