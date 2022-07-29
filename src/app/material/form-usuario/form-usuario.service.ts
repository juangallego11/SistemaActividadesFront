import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from 'src/app/model/Usuario';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FormUsuarioService {

  constructor(private http: HttpClient) { }

  public saveUser(usuario: Usuario): Observable<any> {
    return this.http.post<any>(environment.apiUrl + '/user/', usuario);
  }

  public updateUser(id: number,usuario:Usuario): Observable<any> {
    return this.http.put<any>(environment.apiUrl + '/user/'+id, usuario);
  }
}
