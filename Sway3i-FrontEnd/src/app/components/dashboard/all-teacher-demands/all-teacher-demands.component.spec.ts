import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllTeacherDemandsComponent } from './all-teacher-demands.component';

describe('AllTeacherDemandsComponent', () => {
  let component: AllTeacherDemandsComponent;
  let fixture: ComponentFixture<AllTeacherDemandsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AllTeacherDemandsComponent]
    });
    fixture = TestBed.createComponent(AllTeacherDemandsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
