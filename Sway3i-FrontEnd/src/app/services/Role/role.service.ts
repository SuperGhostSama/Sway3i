import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { RoleRequestDTO } from 'src/app/dto/Role/requests/role-request-DTO';
import { RoleResponseDTO } from 'src/app/dto/Role/responses/role-response-DTO';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  private apiUrl = `${environment.api}role`;

  constructor(private http: HttpClient) { }

  getAllRoles(): Observable<RoleResponseDTO[]> {
    return this.http.get<RoleResponseDTO[]>(this.apiUrl);
  }

  saveRole(roleToSave: RoleRequestDTO): Observable<RoleResponseDTO> {
    return this.http.post<RoleResponseDTO>(this.apiUrl, roleToSave);
  }

  grantAuthorities(authoritiesRequest: any): Observable<RoleResponseDTO> {
    return this.http.put<RoleResponseDTO>(`${this.apiUrl}/grant_authorities`, authoritiesRequest);
  }

  revokeAuthorities(authoritiesRequest: any): Observable<RoleResponseDTO> {
    return this.http.put<RoleResponseDTO>(`${this.apiUrl}/revoke_authorities`, authoritiesRequest);
  }

  grantRoleToUser(grantRoleToUserRequest: any): Observable<RoleResponseDTO> {
    return this.http.post<RoleResponseDTO>(`${this.apiUrl}/grant_role_to_user`, grantRoleToUserRequest);
  }

  deleteRole(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
