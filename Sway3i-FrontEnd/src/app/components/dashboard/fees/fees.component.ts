import { Component } from '@angular/core';

@Component({
  selector: 'app-fees',
  templateUrl: './fees.component.html',
  styleUrls: ['./fees.component.css']
})
export class FeesComponent {
  showForm: boolean = false;

  toggleForm() {
    this.showForm = !this.showForm;
  }
}
