import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable, tap } from 'rxjs';
import { RepositoryResponse, Repository } from './repository.interface';
import { UserService } from './user.service';
import { BACKEND_URL } from './app.constants';

@Injectable({
  providedIn: 'root'
})
export class RepositoryService implements OnInit {
  repositoryResponse!: RepositoryResponse;
  repositories!: Repository[];
  private baseUrl = BACKEND_URL;
  user: any;


  constructor(private http: HttpClient,
    public userService: UserService) { }
  ngOnInit(): void {
  }

  getRepositories(): Observable<RepositoryResponse> {
    const userString = localStorage.getItem("user");
    this.getUser(userString);
    const url = `${this.baseUrl}/getRepositories/${this.user.id}`;
    return this.http.get<RepositoryResponse>(url)
      .pipe(
        tap((response: RepositoryResponse) => this.repositoryResponse = response),
      );
  }

  getCodeScans(): Observable<Number> {
    const userString = localStorage.getItem("user");
    this.getUser(userString);
    const url = `${this.baseUrl}/getCodeScans/${this.user.id}`;
    return this.http.get<Number>(url);
  }

  getCollaborators(): Observable<Number> {
    const userString = localStorage.getItem("user");
    this.getUser(userString);
    const url = `${this.baseUrl}/getCollaborators/${this.user.id}`;
    return this.http.get<Number>(url);
  }


  private getUser(userString: string | null) {
    if (userString) {
      this.user = JSON.parse(userString);
    }
  }
}
