import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable, tap } from 'rxjs';
import { RepositoryResponse, Repository } from './repository.interface';
import { UserService } from './user.service';
import { User } from './user.interface';

@Injectable({
  providedIn: 'root'
})
export class RepositoryService implements OnInit {
  repositoryResponse!: RepositoryResponse;
  repositories!: Repository[];
    private baseUrl = 'http://localhost:3000';
  

  constructor(private http: HttpClient,
    public userService: UserService) { }
  ngOnInit(): void {
    console.log("user in repositories", this.userService.user);
  }

  getRepositories(): Observable<RepositoryResponse> {
    const url = `${this.baseUrl}/getRepositories/${this.userService.user.id}`;
    return this.http.get<RepositoryResponse>(url)
      .pipe(
        tap((response: RepositoryResponse) => this.repositoryResponse = response),
      );
  }
  
}
