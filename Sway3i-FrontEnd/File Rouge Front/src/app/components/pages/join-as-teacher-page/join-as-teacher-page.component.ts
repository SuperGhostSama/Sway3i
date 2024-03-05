import { Component } from '@angular/core';

@Component({
  selector: 'app-join-as-teacher-page',
  templateUrl: './join-as-teacher-page.component.html',
  styleUrls: ['./join-as-teacher-page.component.css']
})
export class JoinAsTeacherPageComponent {
  isRulesVisible : boolean = false;
  rule1 = 'An undergraduate degree';
  rule2 = 'Participate in supervised teaching';
  rule3 = 'State teaching license';
  rule4 = 'Pursue graduate studies';

  showInstructorRequirements() {
    this.isRulesVisible = !this.isRulesVisible;
    this.rule1 = 'Educational Foundation';
    this.rule2 = 'Practical Teaching Experience';
    this.rule3 = 'Licensing Credentials';
    this.rule4 = 'Commitment to Professional Growth';
  }

  showInstructorRules() {
    this.isRulesVisible = !this.isRulesVisible;
    this.rule1 = 'An undergraduate degree';
    this.rule2 = 'Participate in supervised teaching';
    this.rule3 = 'State teaching license';
    this.rule4 = 'Pursue graduate studies';
  }

  
}
