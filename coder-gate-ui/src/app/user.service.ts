import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(@Inject('git_access_token') private gitAccessToken: string, private http: HttpClient) { 
  }
  url = 'https://jsonplaceholder.typicode.com/posts';

  

  public getUsers(): Observable<any> {
    return  this.http.get(this.url);
  }
}


