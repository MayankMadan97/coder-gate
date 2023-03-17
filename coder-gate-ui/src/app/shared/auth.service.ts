import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  private readonly clientId = '0386b3f4c4ac6c744a13';
  private readonly redirectUri = 'http://localhost:4200/dashboard';
  private readonly scope = 'user';

  constructor(private http: HttpClient, private router: Router) { }

  public isLoggedIn(): boolean {
    return !!localStorage.getItem("github_access_token");
  }

  login(): void {
    const authUrl = `https://github.com/login/oauth/authorize?client_id=${this.clientId}&redirect_uri=${this.redirectUri}&scope=${this.scope}`;
    window.location.href = authUrl;
  }

  handleCallback(): any {
    const code = this.getParameterByName('code');
    return this.http.get<string>(`http://localhost:8080/github/access-token?code=${code}`);
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
