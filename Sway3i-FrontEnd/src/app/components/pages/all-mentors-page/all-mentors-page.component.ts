import { Component } from '@angular/core';

@Component({
  selector: 'app-all-mentors-page',
  templateUrl: './all-mentors-page.component.html',
  styleUrls: ['./all-mentors-page.component.css']
})
export class AllMentorsPageComponent {


  ngOnInit(): void {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }
  
}
