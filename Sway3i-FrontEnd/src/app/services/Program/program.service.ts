import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ProgramRequestDTO } from 'src/app/dto/Program/requests/program-request-DTO';
import { ProgramResponseDTO } from 'src/app/dto/Program/responses/program-response-DTO';

@Injectable({
  providedIn: 'root'
})
export class ProgramService {
  private apiUrl = `${environment.api}programs`;

  constructor(private http: HttpClient) { }

  getAllPrograms(): Observable<ProgramResponseDTO[]> {
    return this.http.get<ProgramResponseDTO[]>(this.apiUrl);
  }

  getProgramById(id: number): Observable<ProgramResponseDTO> {
    return this.http.get<ProgramResponseDTO>(`${this.apiUrl}/${id}`);
  }

  createProgram(programRequest: ProgramRequestDTO): Observable<ProgramResponseDTO> {
    return this.http.post<ProgramResponseDTO>(this.apiUrl, programRequest);
  }

  updateProgram(id: number, updatedProgramRequest: ProgramRequestDTO): Observable<ProgramResponseDTO> {
    return this.http.put<ProgramResponseDTO>(`${this.apiUrl}/${id}`, updatedProgramRequest);
  }

  deleteProgram(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
