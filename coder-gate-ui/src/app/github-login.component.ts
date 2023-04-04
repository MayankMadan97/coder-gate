import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from './shared/auth.service';
import { User } from './user.interface';
import { UserService } from './user.service';

@Component({
  selector: 'app-github-login',
  templateUrl: './github-login.component.html',
  styleUrls: ['./github-login.component.css']
})
export class GithubLoginComponent implements OnInit {
  user!: User;


  constructor(
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    public userService: UserService
  ) {}

  ngOnInit(): void {
    const code = this.route.snapshot.queryParamMap.get('code');
    if (code) {
      this.authService.handleCallback().subscribe((response_: any) => {
        const githubAccessToken = response_["access_token"];
        localStorage.setItem("github_access_token", githubAccessToken);
        this.userService.getUsers(githubAccessToken).subscribe(user => {
          this.user = user;
          this.router.navigate(['/dashboard']);
        });
      });
    }
  }
}
