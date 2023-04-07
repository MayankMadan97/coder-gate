import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import * as Highcharts from 'highcharts';
import { BACKEND_URL } from '../app.constants';
import { BranchService } from '../branch.service';
import { Repository, RepositoryResponse } from '../repository.interface';
import { RepositoryService } from '../repository.service';

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


  time_bugs_vuln_code_chartOptions: any = {
    title: {
      text: "Timestamp vs Bugs, Vulnerabilities & Code Smells",
      align: "left"
    },
    subtitle: {
      text: "",
      align: "left"
    },

    yAxis: {
      title: {
        text: "Number of Bugs, Vulnerabilities & Code Smells"
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
      name: '',
      data: [],
      type: 'line'
    },
    {
      name: '',
      data: [],
      type: 'line'
    },
    {
      name: '',
      data: [],
      type: 'line'
    },
    ]
  };


  constructor(private route: ActivatedRoute, private http: HttpClient, private repositoryService: RepositoryService,
    private branchService: BranchService) { }

  repositoryRepsonse!: RepositoryResponse;
  repositories!: Repository[];
  repositoryId!: number;
  branches!: string[];
  userSelectedBranch!: string

  ngOnInit() {

    this.route.params.subscribe(params => {
      this.selectedRepo = params['selectedRepo'];
      this.selectedRepoId = params['repoId'];

    });

    this.branchService.getBranches(this.selectedRepoId || 0).subscribe(branches => {
      this.branches = branches.filter(branch => !branch.includes("http"));

      this.userSelectedBranch = this.branches[0];

      const url = `${BACKEND_URL}/getTimeStampInsight/${this.selectedRepoId}/${this.userSelectedBranch}`;
      this.http.get<any>(url).subscribe(data => {
        this.time_bugs_vuln_code_chartOptions.series[0].name = data.seriesList[0].get("name");
        this.time_bugs_vuln_code_chartOptions.series[0].data[0][0] = data.seriesList[0].get("data").get("dataValuesMap");
        Highcharts.chart('type_number_chart', this.time_bugs_vuln_code_chartOptions);
      });
    });
  }



  type_number_chartOptions: Highcharts.Options = {

    title: {
      text: "Type of Code Smell vs No of Occurrences",
      align: "left"
    },
    subtitle: {
      text: "",
      align: "left"
    },

    tooltip: {
      valueSuffix: ""
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
      categories: ["Implementation", "Design", "Architecture"],
      title: {
        text: "Type of Code Smells"
      },
    },

    yAxis: {
      title: {
        text: "No of Occurrences",
      },
      min: 0,
      max: 100,
      labels: {
        overflow: "justify"
      }
    },


    legend: {
      layout: "vertical",
      align: "right",
      verticalAlign: "top",
      floating: true,
      borderWidth: 1,
      shadow: true
    },

    credits: {
      enabled: false
    },

    series: [{
      name: 'No of Occurences',
      data: [40, 75, 39],
      type: 'column'
    }]
  };

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
