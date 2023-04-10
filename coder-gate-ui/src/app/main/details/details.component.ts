
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RepositoryResponse } from 'src/app/repository.interface';



export interface ThresholdDTO {
  bugs: number;
  vulnerabilities: number;
  codeSmell: number;
  testCoverage: number;
  duplicatedLines: number;
  cyclomaticComplexity: number;
  documentedLines: number;
  cyclicDependency: number;
  godComponents: number;
  cycDependentMod: number;
  insufficientModularization: number;
  unnecessaryAbstraction: number;
  complexConditional: number;
  emptyCatchClause: number;
  missingAssertion: number;
  emptyTest: number;
  archSmellDensity: number;
  designSmellDensity: number;
  impSmellDensity: number;
  allowAction: boolean;
}

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

  links?: any;
  activeLink: string = "Threshold";
  //bugs: AbstractControl | undefined;


  constructor(private route: ActivatedRoute) { }

  public selectedRepo?: string;
  public selectedRepoId?: number;
  repositoryResponse!: RepositoryResponse;

  public ngOnInit() {
    this.route.params.subscribe(params => {
      this.selectedRepo = params['repoName'];
      this.selectedRepoId = params['repoId'];

      this.links = [
        { name: 'Threshold', routerLink: ['/dashboard/repo/', this.selectedRepo, this.selectedRepoId, 'threshold'] },
        { name: 'Insight', routerLink: ['/dashboard/repo/', this.selectedRepo, this.selectedRepoId, 'insights'] }
      ];
    });
  }
}