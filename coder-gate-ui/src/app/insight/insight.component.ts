import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import * as Highcharts from 'highcharts';
import { BACKEND_URL } from '../app.constants';
import { BranchService } from '../branch.service';
import { Repository, RepositoryResponse } from '../repository.interface';
import { RepositoryService } from '../repository.service';

interface ChartData {
  seriesList: ChartSeries[];
}

interface ChartSeries {
  name: string;
  data: ChartDataValues;
}

interface ChartDataValues {
  dataValuesMap: Record<string, number>;
}

interface OccurrencesSeries {
  "Documented Lines": number;
  "CyclomaticComplexity": number;
  "Duplicated Lines": number;
  "Missing Assertion": number;
  "Design Smell Density": number;
  "Complex Conditional": number;
  "Cyclic Dependencies": number;
  "God Components": number;
  "Empty Test": number;
  "Implementation Smell Density": number;
  "Insufficient Modularization": number;
  "Empty Catch Clause": number;
  "Architecture Smell Density": number;
  "Test Coverage": number;
  "Cyclic Dependent Modularization": number;
}

@Component({
  selector: 'app-insights',
  templateUrl: './insight.component.html',
  styleUrls: ['./insight.component.css']
})

export class InsightsComponent implements OnInit {

  public selectedRepo?: string;
  public selectedRepoId?: number;
  public showDropdown = false;
  Highcharts: typeof Highcharts = Highcharts;

  constructor(private route: ActivatedRoute, private http: HttpClient, private repositoryService: RepositoryService,
    private branchService: BranchService) { }

  repositoryRepsonse!: RepositoryResponse;
  repositories!: Repository[];
  repositoryId!: number;
  branches!: string[];
  userSelectedBranch!: string

  smellDensityOccuranceChartOptions: Highcharts.Options = {
    title: {
      text: "Code smell densities",
      align: "center"
    },
    plotOptions: {
      column: {
        pointWidth: 70,
        dataLabels: {
          enabled: true
        }
      }
    },
    xAxis: {
      categories: ["Architecture", "Design", "Implementation"],
      title: {
        text: "Type of Code Smells"
      },
    },
    yAxis: {
      title: {
        text: "Density",
      },
      labels: {
        overflow: "justify"
      }
    },
    credits: {
      enabled: false
    }
  };



  ngOnInit() {

    let params = this.route.parent?.snapshot.params || {};
    this.selectedRepo = params['repoName'];
    this.selectedRepoId = params['repoId'];


    this.branchService.getBranches(this.selectedRepoId || 0).subscribe(branches => {
      this.branches = branches.filter(branch => !branch.includes("http"));

      this.userSelectedBranch = this.branches[0];
      this.getAnalysisData();
    });
  }


  public getAnalysisData() {
    const url = `${BACKEND_URL}/getOccurrencesInsight/${this.selectedRepoId}/${this.userSelectedBranch}`;
    this.http.get<any>(url).subscribe((input: { occurrencesSeries: OccurrencesSeries }) => {
      console.log(JSON.stringify(input));
      this.smellDensityOccuranceChartOptions.series = [{
        name: 'No of Occurences',
        data: [
          Number.parseFloat(input.occurrencesSeries['Architecture Smell Density'].toFixed(4)),
          Number.parseFloat(input.occurrencesSeries['Design Smell Density'].toFixed(4)),
          Number.parseFloat(input.occurrencesSeries['Implementation Smell Density'].toFixed(4))
        ],
        type: 'column'
      }];
      console.log(JSON.stringify(this.smellDensityOccuranceChartOptions))
    });
  }

  time_loc_duplines_chartOptions: Highcharts.Options = {

    title: {
      text: "Timestamp vs LOC & Duplicated Lines",
      align: "left"
    },

    subtitle: {
      text: "",
      align: "left"
    },

    yAxis: {
      title: {
        text: "Lines of Code & Duplicated Lines"
      },
      min: 0,
      max: 100,
      tickInterval: 10
    },

    xAxis: {
      type: 'datetime',
      dateTimeLabelFormats: {
        hour: '%H:%M',
      },
      min: Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate()),
      max: Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 23, 59),
      tickInterval: 3600 * 1000,
      title: {
        text: "Time"
      },
    },

    legend: {
      align: "right",
      verticalAlign: "top",
      layout: "vertical",
      floating: true,
      borderWidth: 1,
      shadow: true
    },

    series: [{
      name: 'Lines of Code',
      data: [
        [Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 0, 0), 25],
        [Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 3, 0), 65],
        [Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 4, 5), 9],
        [Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 5, 59), 85],
        [Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 10, 2), 11],
        [Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 12, 30), 62],
        [Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 13, 40), 25],
        [Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 19, 55), 95],
        [Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 20, 9), 72],
        [Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 23, 2), 20],
      ],
      type: 'line'
    },
    {
      name: 'Dulicated LInes',
      data: [
        [Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 1, 0), 11],
        [Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 6, 0), 25],
        [Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 7, 5), 95],
        [Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 9, 59), 99],
        [Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 11, 2), 1],
        [Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 14, 30), 32],
        [Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 15, 40), 45],
        [Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 18, 55), 35],
        [Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 21, 9), 62],
        [Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 22, 2), 78],
      ],
      type: 'line'
    },
    ]
  };
}
