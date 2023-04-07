import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { BACKEND_URL } from './app.constants';

@Injectable({
  providedIn: 'root'
})
export class BranchService {

  branch!: any;
  baseUrl = BACKEND_URL;

  constructor(private http: HttpClient) { }

  getBranches(repoId: number): Observable<string[]> {
    const url = `${this.baseUrl}/getBranches/${repoId}`;
    return this.http.get<any>(url)
      .pipe(
        tap((response: any) => this.branch = response),
      );
  }


}
