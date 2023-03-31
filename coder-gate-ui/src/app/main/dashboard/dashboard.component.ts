
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Repository, RepositoryResponse } from 'src/app/repository.interface';
import { RepositoryService } from 'src/app/repository.service';
import { ThresholdService } from 'src/app/shared/threshold.service';

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
  cyclicallyDependentModularization: number;
  insufficientModularization: number;
  unnecessaryAbstraction: number;
  complexMethod: number;
  complexConditional: number;
  emptyCatchClause: number;
  missingAssertion: number;
  emptyTest: number;
}

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  

  public ELEMENT_DATA = [{}]
  displayedColumns: string[] = ['name', 'Last updated', 'health'];
  dataSource : any;
  thresholdDTO!: ThresholdDTO;
  myForm?: FormGroup;
  repositories: Repository[] = [];
  //bugs: AbstractControl | undefined;


  constructor(private fb: FormBuilder, private http: HttpClient, private thresholdService: ThresholdService,private repositoryService: RepositoryService) {}
  public selectedRepo?: string;
  repositoryResponse!: RepositoryResponse;
  public showThresholdView = false;

  ngOnInit() {
    this.repositoryService.getRepositories().subscribe((response: RepositoryResponse) => {
      this.repositories = response.repositories;
  
      // Do something with the repositories here
      console.log('Repositories:', this.repositories);
  
      for (let i = 0; i < this.repositories.length; i++) {
        this.ELEMENT_DATA.push({
          title: this.repositories[i].name,
          description: this.repositories[i].name,
          lastUpdatedOn: "1st April 2023",
          health: 100,
          tag: "New"
        });
      }
      this.ELEMENT_DATA.splice(0, 1);
      console.log("element_data",this.ELEMENT_DATA);
      this.dataSource = this.ELEMENT_DATA;
    });
    
  }

  onSubmit() {
    if (this.myForm?.valid) {
      // Do something with the form data here
      //const formValues = this.myForm.value;
      this.thresholdService.postThresholdValues(594890775,this.myForm.value).subscribe(
        response => {
          console.log(response);
        });
    }
  }

  

  public onRepoClick(repo: string) {
    this.selectedRepo = repo;
    this.thresholdService.getThresholdValues(594890775).subscribe(
      response => {
        this.thresholdDTO = response;
        console.log(this.thresholdDTO);
        this.showThresholdView = true;
        this.myForm = this.fb.group({
        bugs: [this.thresholdDTO.bugs, Validators.required],
        vulnerabilities: [this.thresholdDTO.vulnerabilities, Validators.required],
        codeSmell: [this.thresholdDTO.codeSmell, Validators.required],
        testCoverage: [this.thresholdDTO.testCoverage, Validators.required],
        duplicatedLines: [this.thresholdDTO.duplicatedLines, Validators.required],
        cyclomaticComplexity: [this.thresholdDTO.cyclomaticComplexity, Validators.required],
        documentedLines: [this.thresholdDTO.documentedLines, Validators.required],
        cyclicDependency: [this.thresholdDTO.cyclicDependency, Validators.required],
        godComponents: [this.thresholdDTO.godComponents, Validators.required],
        cyclicallyDependentModularization: [this.thresholdDTO.cyclicallyDependentModularization, Validators.required],
        insufficientModularization: [this.thresholdDTO.insufficientModularization, Validators.required],
        unnecessaryAbstraction: [this.thresholdDTO.unnecessaryAbstraction, Validators.required],
        complexMethod: [this.thresholdDTO.complexMethod, Validators.required],
        complexConditional: [this.thresholdDTO.complexConditional, Validators.required],
        emptyCatchClause: [this.thresholdDTO.emptyCatchClause, Validators.required],
        missingAssertion: [this.thresholdDTO.missingAssertion, Validators.required],
        emptyTest: [this.thresholdDTO.emptyTest, Validators.required],
      });
      });
  }
}
