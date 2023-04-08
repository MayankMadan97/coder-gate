import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { BACKEND_URL } from '../app.constants';

import { FRONTEND_URL } from '../app.constants';


@Injectable({
  providedIn: 'root'
})

export class AuthService {

  private readonly clientId = 'b4623432c49d30f9dd5e';
  private readonly redirectUri = FRONTEND_URL+"/github-callback";
  private readonly scope = 'user';

  constructor(private http: HttpClient, private router: Router) { }

  public isLoggedIn(): boolean {
    return !!localStorage.getItem("github_access_token");
  }

  login(): void {
    const authUrl = `https://github.com/login/oauth/authorize?client_id=${this.clientId}&redirect_uri=${this.redirectUri}&scope=${this.scope}`;
    window.location.href = authUrl;
  }

  logout(): void {
    localStorage.removeItem("github_access_token");
    this.router.navigate(["home"]);
  }

  handleCallback(): any {
    const code = this.getParameterByName('code');
    return this.http.get<string>(`${BACKEND_URL}/github/access-token?code=${code}`);
  }

  private getParameterByName(name: string): string | undefined {
    const url = window.location.href;
    name = name.replace(/[\[\]]/g, '\\$&');
    const regex = new RegExp(`[?&]${name}(=([^&#]*)|&|#|$)`);
    const results = regex.exec(url);
    if (!results) {
      return undefined;
    }
    if (!results[2]) {
      return '';
    }
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
  }
}
