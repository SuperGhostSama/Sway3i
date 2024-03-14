import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JoinAsTeacherPageComponent } from './join-as-teacher-page.component';

describe('JoinAsTeacherPageComponent', () => {
  let component: JoinAsTeacherPageComponent;
  let fixture: ComponentFixture<JoinAsTeacherPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JoinAsTeacherPageComponent]
    });
    fixture = TestBed.createComponent(JoinAsTeacherPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
