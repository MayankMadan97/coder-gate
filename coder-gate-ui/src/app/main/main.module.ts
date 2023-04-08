import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatSliderModule } from '@angular/material/slider';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { RouterModule, Routes } from '@angular/router';
import { HighchartsChartModule } from 'highcharts-angular';
import { InsightsComponent } from '../insight/insight.component';
import { AuthGuard } from '../shared/auth.guard';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DetailsComponent } from './details/details.component';
import { MetricsComponent } from './metrics/metrics.component';
import { NavBarComponent } from './navbar/navbar.component';
import { OnSubmitAlert, ThresholdComponent } from './threshold/threshold.component';
const routes: Routes = [
  {
    path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard],
    children: [
      { path: '', component: MetricsComponent },
      {
        path: 'repo/:repoName/:repoId', component: DetailsComponent,
        children: [
          { path: '', redirectTo: 'threshold', pathMatch: "full" }, // Redirect to threshold
          { path: 'threshold', component: ThresholdComponent }, // No need for pathMatch here
          { path: 'insights', component: InsightsComponent }
        ]
      }
    ]
  },
];

@NgModule({
  declarations: [
    DashboardComponent,
    InsightsComponent,
    NavBarComponent,
    OnSubmitAlert,
    MetricsComponent,
    ThresholdComponent,
    DetailsComponent
  ],
  imports: [
    CommonModule,
    MatTableModule,
    HighchartsChartModule,
    MatFormFieldModule,
    MatSliderModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes),
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatTabsModule,
    HttpClientModule,

    MatCheckboxModule,
    MatDialogModule

  ]
})
export class MainModule { }
