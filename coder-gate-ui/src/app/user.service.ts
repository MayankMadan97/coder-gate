import { HttpClient, HttpHeaders,HttpParams } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(@Inject('git_access_token') private gitAccessToken: string, private http: HttpClient) { }

  private apiUrl = 'localhost:3000/getUserDetails';

  public getUsers(): Observable<any> {
    const params = new HttpParams().set('githubAccessToken', this.gitAccessToken);
    return  this.http.get(this.apiUrl,{params});
  }
}


