import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from 'src/app/model/Usuario';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FormClienteService {

  clienteURL = environment.clienteURL;

  constructor(private http: HttpClient) { }

  public saveUser(usuario: any): Observable<any> {
    return this.http.post<any>(this.clienteURL, usuario);
  }

  public updateUser(id: number,usuario:Usuario): Observable<any> {
    return this.http.put<any>(this.clienteURL +id, usuario);
  }
}
