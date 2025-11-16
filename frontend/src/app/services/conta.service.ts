import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ContaCreateRequest, ContaResponse } from '../models/conta.model';

@Injectable({
  providedIn: 'root'
})
export class ContaService {

  // ajuste a URL/porta de acordo com o seu back-end
  private readonly baseUrl = 'http://localhost:8080/api/pay-accounts';

  constructor(private http: HttpClient) {}

  listar(): Observable<ContaResponse[]> {
    return this.http.get<ContaResponse[]>(this.baseUrl);
  }

  criar(conta: ContaCreateRequest): Observable<ContaResponse> {
    return this.http.post<ContaResponse>(this.baseUrl, conta);
  }
}
