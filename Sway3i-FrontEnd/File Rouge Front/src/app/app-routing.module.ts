import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { authGuard } from './guard/auth/auth.guard';
import { AuthLayoutComponent } from './components/layout/auth-layout/auth-layout.component';
import { DasboardLayoutComponent } from './components/layout/dasboard-layout/dasboard-layout.component';
import { LandingPageComponent } from './components/pages/landing-page/landing-page.component';
import { AllMentorsPageComponent } from './components/pages/all-mentors-page/all-mentors-page.component';
import { MentorsCardsComponent } from './components/utils/mentors-cards/mentors-cards.component';
import { JoinAsTeacherPageComponent } from './components/pages/join-as-teacher-page/join-as-teacher-page.component';
import { CoursesListPageComponent } from './components/pages/courses-list-page/courses-list-page.component';
import { CoursesCardsComponent } from './components/utils/courses-cards/courses-cards.component';
import { CoursesPricingComponent } from './components/pages/courses-pricing/courses-pricing.component';

const routes: Routes = [
  {
    path: '',
    component: AuthLayoutComponent,
    children: [
      { path: '', component: LandingPageComponent},
      { path: 'all-mentors', 
        component: AllMentorsPageComponent, 
        children: [
          { path : '' , component: MentorsCardsComponent},
        ]},
      { path: 'join-as-teacher', component: JoinAsTeacherPageComponent },
      { path: 'courses-list', 
        component: CoursesListPageComponent,
        children: [
          {path : '' , component: CoursesCardsComponent}
        ]},
      { path: 'courses-pricing', component: CoursesPricingComponent },
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },
    ],
  },
  {
    path: '',
    component: DasboardLayoutComponent,
    canActivate: [authGuard],
    children: [
      // { path: 'Competitions', component: CompetitionsComponent, canActivate: [authGuard] },
      // { path: 'Members', component: MembersComponent, canActivate: [authGuard] },
      // { path: 'Podium', component: PodiumComponent, canActivate: [authGuard] },
      // { path: 'Results', component: ResultsComponent, canActivate: [authGuard] },
      // { path: 'MyCompetitions', component: MyCompetitionsComponent, canActivate: [authGuard] },
      // { path: 'Verification', component: MemberVerificationComponent, canActivate: [authGuard]}
    ],
  }
]; 

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
