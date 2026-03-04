import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { BeneficioDto, BeneficioEntity, TransferRequest } from './models';

@Injectable({ providedIn: 'root' })
export class BeneficioApiService {
  private readonly base = environment.apiBaseUrl;
  private readonly v1 = `${this.base}/v1/beneficios`;

  constructor(private http: HttpClient) {}

  listAll(): Observable<BeneficioEntity[]> {
    return this.http.get<BeneficioEntity[]>(this.v1);
  }

  findById(id: number): Observable<BeneficioEntity> {
    return this.http.get<BeneficioEntity>(`${this.v1}/${id}`);
  }

  create(dto: BeneficioDto): Observable<BeneficioEntity> {
    return this.http.post<BeneficioEntity>(this.v1, dto);
  }

  update(id: number, dto: BeneficioDto): Observable<BeneficioEntity> {
    return this.http.put<BeneficioEntity>(`${this.v1}/${id}`, dto);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.v1}/${id}`);
  }

  transfer(req: TransferRequest): Observable<void> {
    return this.http.post<void>(`${this.v1}/transfer`, req);
  }
}
