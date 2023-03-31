import { Component } from '@angular/core';
import { Repository, RepositoryResponse } from '../repository.interface';
import { RepositoryService } from '../repository.service';
import { User } from '../user.interface';
import { UserService } from '../user.service';

@Component({
  selector: 'app-navigator',
    templateUrl: './repositories.component.html',
    styleUrls: ['./repositories.component.css']
})
export class RepositoriesComponent {
  user!: User;

  repositories!: RepositoryResponse;

  constructor(private repositoryService: RepositoryService,private userService: UserService) { }

  ngOnInit() {
    console.log('User data retrieved in RepositoryComponent:', this.userService.user.id);
    this.repositoryService.getRepositories()
      .subscribe(repos => this.repositories = repos);
  }
  
  
}
