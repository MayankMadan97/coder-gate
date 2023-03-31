import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { ThresholdService } from 'src/app/shared/threshold.service';
import {MatDialog} from "@angular/material/dialog";



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
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  thresholdDTO!: ThresholdDTO;
  myForm?: FormGroup;
  //bugs: AbstractControl | undefined;

  constructor(private fb: FormBuilder, private thresholdService: ThresholdService, public alert: MatDialog) {}
  
  public selectedRepo?: string;
  public showThresholdView = false;

  ngOnInit() {
  }

  onSubmit() {
    if (this.myForm?.valid) {
      // Do something with the form data here
      //const formValues = this.myForm.value;
      this.thresholdService.postThresholdValues(this.myForm.value, 618842221).subscribe(
        response => {
          console.log(response);
          // alert("Submitted!")
          this.openDialog();
        });
    }
  }

  openDialog() {
    this.alert.open(OnSubmitAlert);
  }

  public ELEMENT_DATA = [
    {
      title: "Java language server",
      description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum lacinia ligula. Donec dictum neque tincidunt lacus rhoncus, in elementum nisi pharetra. Suspendisse velit risus, mollis qui",
      lastUpdatedOn: "27th January 2023",
      health: 48,
      tag: "Featured"
    },
    {
      title: "Postman",
      description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum lacinia ligula. Donec dictum neque tincidunt lacus rhoncus, in elementum nisi pharetra. Suspendisse velit risus, mollis qui",
      lastUpdatedOn: "9th February 2023",
      health: 90,
      tag: "Require attention"
    },
    {
      title: "Apache Maven",
      description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum lacinia ligula. Donec dictum neque tincidunt lacus rhoncus, in elementum nisi pharetra. Suspendisse velit risus, mollis qui",
      lastUpdatedOn: "2nd March 2023",
      health: 21,
      tag: "New"
    },
    {
      title: "Java language server",
      description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum lacinia ligula. Donec dictum neque tincidunt lacus rhoncus, in elementum nisi pharetra. Suspendisse velit risus, mollis qui",
      lastUpdatedOn: "27th January 2023",
      health: 48,
      tag: "Featured"
    },
    {
      title: "Postman",
      description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum lacinia ligula. Donec dictum neque tincidunt lacus rhoncus, in elementum nisi pharetra. Suspendisse velit risus, mollis qui",
      lastUpdatedOn: "9th February 2023",
      health: 90,
      tag: "Require attention"
    }
    ,
    {
      title: "Apache Maven",
      description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum lacinia ligula. Donec dictum neque tincidunt lacus rhoncus, in elementum nisi pharetra. Suspendisse velit risus, mollis qui",
      lastUpdatedOn: "2nd March 2023",
      health: 21,
      tag: "New"
    }
    ,
    {
      title: "Apache Maven",
      description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum lacinia ligula. Donec dictum neque tincidunt lacus rhoncus, in elementum nisi pharetra. Suspendisse velit risus, mollis qui",
      lastUpdatedOn: "2nd March 2023",
      health: 21,
      tag: "New"
    }
    ,
    {
      title: "Apache Maven",
      description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum lacinia ligula. Donec dictum neque tincidunt lacus rhoncus, in elementum nisi pharetra. Suspendisse velit risus, mollis qui",
      lastUpdatedOn: "2nd March 2023",
      health: 21,
      tag: "New"
    }
    ,
    {
      title: "Apache Maven",
      description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum lacinia ligula. Donec dictum neque tincidunt lacus rhoncus, in elementum nisi pharetra. Suspendisse velit risus, mollis qui",
      lastUpdatedOn: "2nd March 2023",
      health: 21,
      tag: "New"
    }
    ,
    {
      title: "Apache Maven",
      description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum lacinia ligula. Donec dictum neque tincidunt lacus rhoncus, in elementum nisi pharetra. Suspendisse velit risus, mollis qui",
      lastUpdatedOn: "2nd March 2023",
      health: 21,
      tag: "New"
    }
  ];

  displayedColumns: string[] = ['name', 'Last updated', 'health'];
  dataSource = this.ELEMENT_DATA;

  public onRepoClick(repo: string) {
    this.selectedRepo = repo;
    this.thresholdService.getThresholdValues(618842221).subscribe(
      response => {
        if(response == null) {
        
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
          cyclicallyDependentModularization: [1, Validators.required],
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
          cyclicallyDependentModularization: [this.thresholdDTO.cyclicallyDependentModularization, Validators.required],
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
      this.showThresholdView = true;
    });
    
  }
}

@Component({
  selector: 'alert-content',
  templateUrl: './alert-content.html'
})

export class OnSubmitAlert {}