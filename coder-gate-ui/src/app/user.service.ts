import { HttpClient, HttpHeaders,HttpParams } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { User } from './user.interface';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  user: User = {
    login: '',
    id: 0,
    node_id: '',
    avatar_url: '',
    gravatar_id: '',
    url: '',
    html_url: '',
    followers_url: '',
    following_url: '',
    gists_url: '',
    starred_url: '',
    subscriptions_url: '',
    organizations_url: '',
    repos_url: '',
    events_url: '',
    received_events_url: '',
    type: '',
    site_admin: false,
    name: null,
    company: null,
    blog: '',
    location: null,
    email: null,
    hireable: null,
    bio: null,
    twitter_username: null,
    public_repos: 0,
    public_gists: 0,
    followers: 0,
    following: 0,
    created_at: '',
    updated_at: '',
    private_gists: 0,
    total_private_repos: 0,
    owned_private_repos: 0,
    disk_usage: 0,
    collaborators: 0,
    two_factor_authentication: false,
    plan: {
      name: '',
      space: 0,
      collaborators: 0,
      private_repos: 0
    }
  };
  constructor(private http: HttpClient) { }

  private baseUrl = 'http://localhost:3000';
  public getUsers(githubAccessToken: string): Observable<User> {
    const url = `${this.baseUrl}/getUserDetails?githubAccessToken=${githubAccessToken}`;
    return this.http.get<User>(url).pipe(
    tap(user => {
      this.user = user;
      console.log('User data stored in UserService:', this.user);
    })
  );
  
  }
}


