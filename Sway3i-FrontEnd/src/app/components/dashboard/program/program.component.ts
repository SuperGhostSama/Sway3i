import { Component } from '@angular/core';

@Component({
  selector: 'app-program',
  templateUrl: './program.component.html',
  styleUrls: ['./program.component.css']
})
export class ProgramComponent {
  showForm: boolean = false;

  toggleForm() {
    this.showForm = !this.showForm;
  }

}
