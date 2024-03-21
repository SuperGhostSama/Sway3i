import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { StudentsInCourseRequestDTO } from 'src/app/dto/StudentInCourse/requests/student-in-course-request-DTO';
import { EnrolledCourseResponseDTO } from 'src/app/dto/StudentInCourse/responses/enrolled-course-response-DTO';
import { StudentsInCourseResponseDTO } from 'src/app/dto/StudentInCourse/responses/student-in-course-response-DTO';

@Injectable({
  providedIn: 'root'
})
export class StudentsInCourseService {
  private apiUrl = `${environment.api}students-in-courses`;

  constructor(private http: HttpClient) { }

  getAllStudentsInCourses(): Observable<StudentsInCourseResponseDTO[]> {
    return this.http.get<StudentsInCourseResponseDTO[]>(this.apiUrl);
  }

  getStudentsInCourseById(id: number): Observable<StudentsInCourseResponseDTO> {
    return this.http.get<StudentsInCourseResponseDTO>(`${this.apiUrl}/${id}`);
  }

  createStudentsInCourse(studentsInCourseRequest: StudentsInCourseRequestDTO): Observable<StudentsInCourseResponseDTO> {
    return this.http.post<StudentsInCourseResponseDTO>(this.apiUrl, studentsInCourseRequest);
  }

  updateStudentsInCourse(id: number, updatedStudentsInCourseRequest: StudentsInCourseRequestDTO): Observable<StudentsInCourseResponseDTO> {
    return this.http.put<StudentsInCourseResponseDTO>(`${this.apiUrl}/${id}`, updatedStudentsInCourseRequest);
  }

  deleteStudentsInCourse(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getEnrolledCoursesByStudentId(studentId: number): Observable<EnrolledCourseResponseDTO[]> {
    return this.http.get<EnrolledCourseResponseDTO[]>(`${this.apiUrl}/enrolled-courses/${studentId}`);
  }


  isStudentEnrolled(studentId: number, courseId: number): any {    
    return this.http.get<boolean>(`${this.apiUrl}/students/enrolled?userId=${studentId}&courseId=${courseId}`);
  }




}
