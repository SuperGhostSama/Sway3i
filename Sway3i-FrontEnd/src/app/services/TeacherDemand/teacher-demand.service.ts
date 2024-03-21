import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TeacherDemandRequestDTO } from 'src/app/dto/TeacherDemand/requests/teacher-demand-request-DTO';
import { TeacherDemandResponseDTO } from 'src/app/dto/TeacherDemand/responses/teacher-demand-response-DTO';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TeacherDemandService {
  private apiUrl = `${environment.api}teacher-demands`;

  constructor(private http: HttpClient) { }

  getAllTeacherDemands(): Observable<TeacherDemandResponseDTO[]> {
    return this.http.get<TeacherDemandResponseDTO[]>(this.apiUrl);
  }

  getTeacherDemandById(id: number): Observable<TeacherDemandResponseDTO> {
    return this.http.get<TeacherDemandResponseDTO>(`${this.apiUrl}/${id}`);
  }

  createTeacherDemand(teacherDemandRequest: TeacherDemandRequestDTO): Observable<TeacherDemandResponseDTO> {
    return this.http.post<TeacherDemandResponseDTO>(this.apiUrl, teacherDemandRequest);
  }

  deleteTeacherDemand(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  acceptTeacherDemand(id: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/${id}/accept`, null);
  }

  rejectTeacherDemand(id: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/${id}/reject`, null);
  }

  getTeacherDemandsByEmail(email: string): Observable<TeacherDemandResponseDTO[]> {
    return this.http.get<TeacherDemandResponseDTO[]>(`${this.apiUrl}/teacher-demands?email=${email}`);
  }
}
