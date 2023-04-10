import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatSelectChange } from '@angular/material/select';
import { ActivatedRoute } from '@angular/router';
import * as Highcharts from 'highcharts';
import More from 'highcharts/highcharts-more';
import Exporting from 'highcharts/modules/exporting';
import { BACKEND_URL } from '../app.constants';
import { BranchService } from '../branch.service';
import { Repository, RepositoryResponse } from '../repository.interface';
import { RepositoryService } from '../repository.service';

More(Highcharts);
Exporting(Highcharts)

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

  packedBubbleSmells: Highcharts.Options = {
    chart: {
      type: 'packedbubble',
      height: '100%'
    },
    title: {
      text: 'Smell Types and Occurances',
      align: 'left'
    },
    tooltip: {
      useHTML: true,
      pointFormat: '<b>{point.name}:</b> {point.value}'
    },
    plotOptions: {
      packedbubble: {
        layoutAlgorithm: {
          splitSeries: false,
          gravitationalConstant: 0.02
        },
        dataLabels: {
          enabled: true,
          format: '{point.name}',
          filter: {
            property: 'y',
            operator: '>',
            value: 250
          },
          style: {
            color: 'black',
            textOutline: 'none',
            fontWeight: 'normal'
          }
        }
      }
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

  public onOptionChange(branch: string) {
    this.userSelectedBranch = branch;
    this.getAnalysisData();
  }


  public getAnalysisData() {
    this.packedBubbleSmells.series = undefined;
    this.densityTimeline.series = undefined;
    this.smellDensityOccuranceChartOptions.series = undefined;
    const occurencesUrl = `${BACKEND_URL}/getOccurrencesInsight/${this.selectedRepoId}/${this.userSelectedBranch}`;
    const timelineUrl = `${BACKEND_URL}/getTimeStampInsight/${this.selectedRepoId}/${this.userSelectedBranch}`;
    this.http.get<any>(occurencesUrl).subscribe((input: { occurrencesSeries: OccurrencesSeries }) => {
      this.smellDensityOccuranceChartOptions.series = [{
        name: 'No of Occurences',
        data: [
          Number.parseFloat(input.occurrencesSeries['Architecture Smell Density'].toFixed(4)),
          Number.parseFloat(input.occurrencesSeries['Design Smell Density'].toFixed(4)),
          Number.parseFloat(input.occurrencesSeries['Implementation Smell Density'].toFixed(4))
        ],
        type: 'column'
      }];

      this.packedBubbleSmells.series = [{
        type: 'packedbubble', // Set the type to 'packedbubble'
        data: Object.entries(input.occurrencesSeries).filter(entry => entry[1] != 0 && entry[1] != -1
          && !(entry[0].includes("Density") || entry[0].includes("LOC")
            || entry[0].includes("Smell") || entry[0].includes("Count"))).map(([key, value]) => ({
              name: key,
              value: value
            }))
      }];
    });

    this.http.get<any>(timelineUrl).subscribe((input: { seriesList: Series[] }) => {
      console.log(JSON.stringify(input));
      if (input?.seriesList.length > 0) {
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
      }
    });
  }

}
