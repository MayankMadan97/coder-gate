import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-github-login',
  templateUrl: './github-login.component.html',
  styleUrls: ['./github-login.component.css']
})
export class GithubLoginComponent {
  private readonly clientId = '0386b3f4c4ac6c744a13';
  private readonly redirectUri = 'http://localhost:4200/dashboard';
  private readonly scope = 'user';

  constructor(private http: HttpClient, private router: Router) { }

  login(): void {
    const authUrl = `https://github.com/login/oauth/authorize?client_id=${this.clientId}&redirect_uri=${this.redirectUri}&scope=${this.scope}`;
    window.location.href = authUrl;
  }

  handleCallback(): void {
    const code = this.getParameterByName('code');
    this.http.post('https://github.com/login/oauth/access_token', {
      client_id: this.clientId,
      client_secret: '8ac52e1201e725cc571542212eba1b746f9431b6',
      code: code,
      redirect_uri: this.redirectUri,
    }).subscribe((data: any) => {
      const accessToken = data.access_token;
      localStorage.setItem('github_access_token', accessToken);
      AuthService.isAuthenticated = true;
      this.router.navigate(['/dashboard']);
    });
  }

  private getParameterByName(name: string, url?: string): string | null {
    if (!url) {
      url = window.location.href;
    }
    name = name.replace(/[[]]/g, '\\$&');
    const regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)');
    const results = regex.exec(url);
    if (!results) {
      return null;
    }
    if (!results[2]) {
      return '';
    }
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
  }
}
