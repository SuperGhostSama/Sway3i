import { TestBed } from '@angular/core/testing';

import { TeacherDemandService } from './teacher-demand.service';

describe('TeacherDemandService', () => {
  let service: TeacherDemandService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TeacherDemandService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
