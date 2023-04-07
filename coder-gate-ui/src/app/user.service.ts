import { HttpClient, HttpHeaders,HttpParams } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { BACKEND_URL } from './app.constants';



@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }

  private baseUrl = BACKEND_URL;
  public getUsers(githubAccessToken: string): Observable<any> {
    const url = `${this.baseUrl}/getUserDetails?githubAccessToken=${githubAccessToken}`;
    return this.http.get<any>(url).pipe(
    tap(user => {
      localStorage.setItem("user", JSON.stringify(user));
    })
  );

  }

}


