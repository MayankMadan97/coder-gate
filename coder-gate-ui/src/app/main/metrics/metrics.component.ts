
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from "@angular/material/dialog";
import { Repository, RepositoryResponse } from 'src/app/repository.interface';
import { RepositoryService } from 'src/app/repository.service';
import { ThresholdService } from 'src/app/shared/threshold.service';

@Component({
  selector: 'app-metrics',
  templateUrl: './metrics.component.html',
  styleUrls: ['./metrics.component.css']
})
export class MetricsComponent implements OnInit {



  public ELEMENT_DATA = [{}]
  displayedColumns: string[] = ['name', 'Last updated', 'health'];
  dataSource: any;
  repositories: Repository[] = [];
  repoId: number = 0;
  //bugs: AbstractControl | undefined;


  constructor(private fb: FormBuilder, private http: HttpClient, private repositoryService: RepositoryService, public alert: MatDialog) { }

  public selectedRepo?: string;
  repositoryResponse!: RepositoryResponse;
  public showThresholdView = false;

  resetRepoId() {

    this.repoId = 0;
    this.showThresholdView = false;
  }

  ngOnInit() {
    this.repositoryService.getRepositories().subscribe((response: RepositoryResponse) => {
      this.repositories = response.repositories;

      // Do something with the repositories here
      console.log('Repositories:', this.repositories);

      for (let i = 0; i < this.repositories.length; i++) {
        this.ELEMENT_DATA.push({
          id: this.repositories[i].id,
          title: this.repositories[i].name,
          description: this.repositories[i].name,
          lastUpdatedOn: "1st April 2023",
          health: 100,
          tag: "New"
        });
      }
      this.ELEMENT_DATA.splice(0, 1);
      console.log("element_data", this.ELEMENT_DATA);
      this.dataSource = this.ELEMENT_DATA;
    });

  }
}