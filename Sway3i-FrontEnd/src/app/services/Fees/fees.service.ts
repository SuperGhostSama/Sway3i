import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { FeesRequestDTO } from 'src/app/dto/Fees/requests/fees-request-DTO';
import { FeesResponseDTO } from 'src/app/dto/Fees/responses/fees-response-DTO';

@Injectable({
  providedIn: 'root'
})
export class FeesService {
  private apiUrl = `${environment.api}/fees`;

  constructor(private http: HttpClient) { }

  getAllFees(): Observable<FeesResponseDTO[]> {
    return this.http.get<FeesResponseDTO[]>(this.apiUrl);
  }

  getFeesById(id: number): Observable<FeesResponseDTO> {
    return this.http.get<FeesResponseDTO>(`${this.apiUrl}/${id}`);
  }

  createFees(feesRequest: FeesRequestDTO): Observable<FeesResponseDTO> {
    return this.http.post<FeesResponseDTO>(this.apiUrl, feesRequest);
  }

  updateFees(id: number, updatedFeesRequest: FeesRequestDTO): Observable<FeesResponseDTO> {
    return this.http.put<FeesResponseDTO>(`${this.apiUrl}/${id}`, updatedFeesRequest);
  }

  deleteFees(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
