import { Injectable, NgModule } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class ApiClientService {
  private baseUrl = 'https://localhost:5001/';

  constructor(private http: HttpClient) {}

  getDocs(query: string): Observable<string[]> {
    return this.http.get<string[]>(this.baseUrl + query);
  }
}
