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
import { AboutCoursePageComponent } from './components/pages/about-course-page/about-course-page.component';
import { TeacherDemandComponent } from './components/dashboard/teacher-demand/teacher-demand.component';
import { CoursesComponent } from './components/dashboard/courses/courses.component';
import { FeesComponent } from './components/dashboard/fees/fees.component';
import { ProgramComponent } from './components/dashboard/program/program.component';
import { UsersComponent } from './components/dashboard/users/users.component';

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
      { path: 'about-course', component: AboutCoursePageComponent },
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },
    ],
  },
  {
    path: 'dashboard',
    component: DasboardLayoutComponent,
    children: [
      { path: 'teacher-demand', component: TeacherDemandComponent },
      { path: 'courses', component: CoursesComponent },
      { path: 'fees', component: FeesComponent },
      { path: 'program', component: ProgramComponent },
      { path: 'users', component: UsersComponent },
    ],
  }
]; 

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
