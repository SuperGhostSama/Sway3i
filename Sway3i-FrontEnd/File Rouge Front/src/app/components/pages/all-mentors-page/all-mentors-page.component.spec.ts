import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllMentorsPageComponent } from './all-mentors-page.component';

describe('AllMentorsPageComponent', () => {
  let component: AllMentorsPageComponent;
  let fixture: ComponentFixture<AllMentorsPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AllMentorsPageComponent]
    });
    fixture = TestBed.createComponent(AllMentorsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
