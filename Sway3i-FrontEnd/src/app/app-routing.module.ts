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
import { CoursesComponent } from './components/dashboard/all-courses/courses.component';
import { FeesComponent } from './components/dashboard/fees/fees.component';
import { ProgramComponent } from './components/dashboard/program/program.component';
import { UsersComponent } from './components/dashboard/users/users.component';
import { AllTeacherDemandsComponent } from './components/dashboard/all-teacher-demands/all-teacher-demands.component';
import { MyCoursesComponent } from './components/dashboard/my-courses/my-courses.component';
import { adminGuard } from './guard/admin/admin.guard';
import { teacherGuard } from './guard/teacher/teacher.guard';
import { loggedInGuard } from './guard/logged-in/logged-in.guard';

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
      { path: 'about-course/:id', component: AboutCoursePageComponent },
      { path: 'login', component: LoginComponent, canActivate: [loggedInGuard]},
      { path: 'register', component: RegisterComponent, canActivate: [loggedInGuard] },
    ],
  },
  {
    path: 'dashboard',
    component: DasboardLayoutComponent,
    canActivate: [authGuard],
    children: [
      { path: 'teacher-demand', component: TeacherDemandComponent, canActivate: [teacherGuard] },
      { path: 'all-teachers-demands', component: AllTeacherDemandsComponent, canActivate: [adminGuard] },
      { path: 'courses', component: CoursesComponent, canActivate: [adminGuard] },
      { path: 'my-courses', component: MyCoursesComponent, canActivate: [teacherGuard] },
      { path: 'fees', component: FeesComponent, canActivate: [adminGuard] },
      { path: 'program', component: ProgramComponent, canActivate: [adminGuard] },
      { path: 'users', component: UsersComponent, canActivate: [adminGuard]},
    ],
  }
]; 

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
