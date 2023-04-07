import { Component } from '@angular/core';
import { Repository, RepositoryResponse } from '../repository.interface';
import { RepositoryService } from '../repository.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-navigator',
    templateUrl: './repositories.component.html',
    styleUrls: ['./repositories.component.css']
})
export class RepositoriesComponent {

  repositories!: RepositoryResponse;

  constructor(private repositoryService: RepositoryService,private userService: UserService) { }

  ngOnInit() {
    this.repositoryService.getRepositories()
      .subscribe(repos => this.repositories = repos);
  }
  
  
}
