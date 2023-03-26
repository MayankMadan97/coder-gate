import { HttpClient, HttpHeaders,HttpParams } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(@Inject('git_access_token') private gitAccessToken: string, private http: HttpClient) { }

  private baseUrl = 'http://localhost:3000';
  public getUsers(): Observable<any> {
    const url = `${this.baseUrl}/getUserDetails?githubAccessToken=${this.gitAccessToken}`;
    return this.http.get<any>(url);
  
  }
}


