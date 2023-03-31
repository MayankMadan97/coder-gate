
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
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
  thresholdDTO!: ThresholdDTO;
  myForm?: FormGroup;
  //bugs: AbstractControl | undefined;


  constructor(private fb: FormBuilder, private http: HttpClient, private thresholdService: ThresholdService) {}

  public selectedRepo?: string;
  public showThresholdView = false;

  ngOnInit() {
  }

  onSubmit() {
    if (this.myForm?.valid) {
      // Do something with the form data here
      //const formValues = this.myForm.value;
      this.thresholdService.postThresholdValues(this.myForm.value).subscribe(
        response => {
          console.log(response);
        });
    }
  }
userList = JSON.parse(localStorage.getItem('userList') || 'null') ; // Use the logical OR operator to assign an empty array if userList is null

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
    this.thresholdService.getThresholdValues().subscribe(
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
