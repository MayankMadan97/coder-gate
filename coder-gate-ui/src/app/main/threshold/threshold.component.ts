
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from "@angular/material/dialog";
import { RepositoryResponse } from 'src/app/repository.interface';
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
  selector: 'app-threshold',
  templateUrl: './Threshold.component.html',
  styleUrls: ['./Threshold.component.css']
})
export class ThresholdComponent implements OnInit {

  public ELEMENT_DATA = [{}]
  dataSource: any;
  thresholdDTO!: ThresholdDTO;
  myForm?: FormGroup;
  repoId: number = 0;
  //bugs: AbstractControl | undefined;


  constructor(private fb: FormBuilder, private http: HttpClient, private thresholdService: ThresholdService, private repositoryService: RepositoryService, public alert: MatDialog) { }

  public selectedRepo?: string;
  repositoryResponse!: RepositoryResponse;

  openDialog() { this.alert.open(OnSubmitAlert); }

  onSubmit() {
    console.log(this.myForm?.status);
    if (this.myForm?.valid) {
      // Do something with the form data here
      //const formValues = this.myForm.value;
      console.log("Inside onSubmit()", this.repoId)
      this.thresholdService.postThresholdValues(this.myForm.value, this.repoId).subscribe(
        response => {
          console.log(response);
          console.log("repoId in threshold,POST", this.repoId);
          this.openDialog();
        });
    }
  }

  public ngOnInit() {
    console.log("repoId in threshold,GET", this.repoId);
    this.thresholdService.getThresholdValues(this.repoId).subscribe(
      response => {
        if (response == null) {

          this.myForm = this.fb.group({
            // bugs: [this.thresholdDTO.bugs, Validators.required],
            // vulnerabilities: [this.thresholdDTO.vulnerabilities, Validators.required],
            codeSmell: [1, Validators.required],
            // testCoverage: [this.thresholdDTO.testCoverage, Validators.required],
            duplicatedLines: [1, Validators.required],
            cyclomaticComplexity: [1, Validators.required],
            // documentedLines: [1, Validators.required],
            cyclicDependency: [1, Validators.required],
            godComponents: [1, Validators.required],
            cycDependentMod: [1, Validators.required],
            insufficientModularization: [1, Validators.required],
            unnecessaryAbstraction: [1, Validators.required],
            complexConditional: [1, Validators.required],
            emptyCatchClause: [1, Validators.required],
            missingAssertion: [1, Validators.required],
            emptyTest: [1, Validators.required],
            archSmellDensity: [1, Validators.required],
            designSmellDensity: [1, Validators.required],
            impSmellDensity: [1, Validators.required],
            allowAction: [false, Validators.required]
          });
        } else {
          this.thresholdDTO = response;
          console.log(this.thresholdDTO);
          this.myForm = this.fb.group({
            // bugs: [this.thresholdDTO.bugs, Validators.required],
            // vulnerabilities: [this.thresholdDTO.vulnerabilities, Validators.required],
            codeSmell: [this.thresholdDTO.codeSmell, Validators.required],
            // testCoverage: [this.thresholdDTO.testCoverage, Validators.required],
            duplicatedLines: [this.thresholdDTO.duplicatedLines, Validators.required],
            cyclomaticComplexity: [this.thresholdDTO.cyclomaticComplexity, Validators.required],
            documentedLines: [this.thresholdDTO.documentedLines, Validators.required],
            cyclicDependency: [this.thresholdDTO.cyclicDependency, Validators.required],
            godComponents: [this.thresholdDTO.godComponents, Validators.required],
            cycDependentMod: [this.thresholdDTO.cycDependentMod, Validators.required],
            insufficientModularization: [this.thresholdDTO.insufficientModularization, Validators.required],
            unnecessaryAbstraction: [this.thresholdDTO.unnecessaryAbstraction, Validators.required],
            complexConditional: [this.thresholdDTO.complexConditional, Validators.required],
            emptyCatchClause: [this.thresholdDTO.emptyCatchClause, Validators.required],
            missingAssertion: [this.thresholdDTO.missingAssertion, Validators.required],
            emptyTest: [this.thresholdDTO.emptyTest, Validators.required],
            archSmellDensity: [this.thresholdDTO.archSmellDensity, Validators.required],
            designSmellDensity: [this.thresholdDTO.designSmellDensity, Validators.required],
            impSmellDensity: [this.thresholdDTO.impSmellDensity, Validators.required],
            allowAction: [this.thresholdDTO.allowAction, Validators.required]
          });
          console.log(this.thresholdDTO.allowAction);
        }
      });


  }


}

@Component({
  selector: 'alert-content',
  templateUrl: './alert-content.html'
})

export class OnSubmitAlert { }
