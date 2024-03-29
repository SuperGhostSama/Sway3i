import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SideBarComponent } from './components/navigation/side-bar/side-bar.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthInterceptor } from './interceptor/auth/auth.interceptor';
import { AuthLayoutComponent } from './components/layout/auth-layout/auth-layout.component';
import { DasboardLayoutComponent } from './components/layout/dasboard-layout/dasboard-layout.component';
import { NavbarComponent } from './components/navigation/navbar/navbar.component';
import { NotificationsComponent } from './components/utils/notifications/notifications.component';
import { FooterComponent } from './components/utils/footer/footer.component';
import { NewsLetterComponent } from './components/utils/news-letter/news-letter.component';
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
import { RouterModule } from '@angular/router';
import { FeesComponent } from './components/dashboard/fees/fees.component';
import { ProgramComponent } from './components/dashboard/program/program.component';
import { UsersComponent } from './components/dashboard/users/users.component';
import { AllTeacherDemandsComponent } from './components/dashboard/all-teacher-demands/all-teacher-demands.component';
import { MyCoursesComponent } from './components/dashboard/my-courses/my-courses.component';
import { InventoryComponent } from './components/dashboard/inventory/inventory.component';

@NgModule({
  declarations: [
    AppComponent,
    SideBarComponent,
    LoginComponent,
    RegisterComponent,
    AuthLayoutComponent,
    DasboardLayoutComponent,
    NavbarComponent,
    NotificationsComponent,
    FooterComponent,
    NewsLetterComponent,
    LandingPageComponent,
    AllMentorsPageComponent,
    MentorsCardsComponent,
    JoinAsTeacherPageComponent,
    CoursesListPageComponent,
    CoursesCardsComponent,
    CoursesPricingComponent,
    AboutCoursePageComponent,
    TeacherDemandComponent,
    CoursesComponent,
    FeesComponent,
    ProgramComponent,
    UsersComponent,
    AllTeacherDemandsComponent,
    MyCoursesComponent,
    InventoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
