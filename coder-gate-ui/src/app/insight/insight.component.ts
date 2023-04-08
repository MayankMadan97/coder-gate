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

interface DataValuesMap {
  [timestamp: string]: number;
}

interface SeriesData {
  dataValuesMap: DataValuesMap;
}

interface Series {
  name: string;
  data: SeriesData;
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

  densityTimeline: Highcharts.Options = {
    title: {
      text: "Timestamp vs Smell Density",
      align: "center"
    },
    yAxis: {
      title: {
        text: "Density"
      }
    },
    xAxis: {
      type: 'datetime',
      dateTimeLabelFormats: {
        hour: '%H:%M',
      },
      title: {
        text: "Time"
      },
    },
    legend: {
      align: "right",
      verticalAlign: "top",
      layout: "horizontal",
      floating: false
    },
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
    const occurencesUrl = `${BACKEND_URL}/getOccurrencesInsight/${this.selectedRepoId}/${this.userSelectedBranch}`;
    const timelineUrl = `${BACKEND_URL}/getTimeStampInsight/${this.selectedRepoId}/${this.userSelectedBranch}`;
    this.http.get<any>(occurencesUrl).subscribe((input: { occurrencesSeries: OccurrencesSeries }) => {
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

    this.http.get<any>(timelineUrl).subscribe((input: { seriesList: Series[] }) => {
      console.log(JSON.stringify(input));
      this.densityTimeline.series = [
        {
          name: 'Architectural',
          data: input.seriesList.filter(item => item.name.includes("Architectural")).map(series => {
            const dataValuesMap = series.data.dataValuesMap;
            return Object.keys(dataValuesMap).map(timestamp =>
              [parseInt(timestamp), Number.parseFloat(dataValuesMap[timestamp].toFixed(4))]
            );
          }).flat(),
          type: 'line'
        },
        {
          name: 'Design',
          data: input.seriesList.filter(item => item.name.includes("Design")).map(series => {
            const dataValuesMap = series.data.dataValuesMap;
            return Object.keys(dataValuesMap).map(timestamp =>
              [parseInt(timestamp), Number.parseFloat(dataValuesMap[timestamp].toFixed(4))]
            );
          }).flat(),
          type: 'line'
        },
        {
          name: 'Implementation',
          data: input.seriesList.filter(item => item.name.includes("Implementation")).map(series => {
            const dataValuesMap = series.data.dataValuesMap;
            return Object.keys(dataValuesMap).map(timestamp =>
              [parseInt(timestamp), Number.parseFloat(dataValuesMap[timestamp].toFixed(4))]
            );
          }).flat(),
          type: 'line'
        }
      ];
      console.log(JSON.stringify(this.densityTimeline))
    });
  }

}
