import { Component } from '@angular/core';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent {

  ngOnInit(): void {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }


}
