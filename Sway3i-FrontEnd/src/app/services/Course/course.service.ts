import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CourseRequestDTO } from 'src/app/dto/Course/requests/course-request-DTO';
import { CourseDetailsResponseDTO } from 'src/app/dto/Course/responses/course-details-reponse-DTO';
import { CourseResponseDTO } from 'src/app/dto/Course/responses/course-reponse-DTO';
import { CourseWithDetailsResponseDTO } from 'src/app/dto/Course/responses/course-with-details-response-DTO';

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  private apiUrl = `${environment.api}courses`;

  constructor(private http: HttpClient) { }

  getAllCourses(): Observable<CourseWithDetailsResponseDTO[]> {
    return this.http.get<CourseWithDetailsResponseDTO[]>(`${this.apiUrl}/all`);
  }

  getCourseById(id: number): Observable<CourseWithDetailsResponseDTO> {
    return this.http.get<CourseWithDetailsResponseDTO>(`${this.apiUrl}/${id}`);
  }

  getAllCoursesByEmail(email: string): Observable<CourseWithDetailsResponseDTO[]> {
    return this.http.get<CourseWithDetailsResponseDTO[]>(`${this.apiUrl}/byEmail?email=${email}`);
  }

  getCourseDetails(courseId: number): Observable<CourseDetailsResponseDTO> {
    return this.http.get<CourseDetailsResponseDTO>(`${this.apiUrl}/${courseId}/details`);
  }

  createCourse(courseRequest: CourseRequestDTO): Observable<CourseResponseDTO> {
    return this.http.post<CourseResponseDTO>(this.apiUrl, courseRequest);
  }

  updateCourse(id: number, updatedCourseRequest: CourseRequestDTO): Observable<CourseResponseDTO> {
    return this.http.put<CourseResponseDTO>(`${this.apiUrl}/${id}`, updatedCourseRequest);
  }

  deleteCourse(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getAllLatestCourses(): Observable<CourseWithDetailsResponseDTO[]> {
    return this.http.get<CourseWithDetailsResponseDTO[]>(`${this.apiUrl}/latest`);
  }
  
}
