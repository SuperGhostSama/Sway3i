import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherDemandComponent } from './teacher-demand.component';

describe('TeacherDemandComponent', () => {
  let component: TeacherDemandComponent;
  let fixture: ComponentFixture<TeacherDemandComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TeacherDemandComponent]
    });
    fixture = TestBed.createComponent(TeacherDemandComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
