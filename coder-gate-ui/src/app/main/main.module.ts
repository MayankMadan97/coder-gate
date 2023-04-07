import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatSliderModule } from '@angular/material/slider';
import { MatTableModule } from '@angular/material/table';
import { RouterModule, Routes } from '@angular/router';
import { HighchartsChartModule } from 'highcharts-angular';
import { AuthGuard } from '../shared/auth.guard';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MetricsComponent } from './metrics/metrics.component';
import { NavBarComponent } from './navbar/navbar.component';


import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDialogModule } from '@angular/material/dialog';
import { InsightsComponent } from '../insight/insight.component';
import { OnSubmitAlert, ThresholdComponent } from './threshold/threshold.component';
const routes: Routes = [
  {
    path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard],
    children: [
      { path: '', redirectTo: 'metrics', pathMatch: 'full' },
      { path: 'metrics', component: MetricsComponent },
      { path: 'threshold/:repoName/:repoId', component: ThresholdComponent },
      { path: 'insights/:repoName/:repoId', component: InsightsComponent }
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
    ThresholdComponent
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

    HttpClientModule,

    MatCheckboxModule,
    MatDialogModule

  ]
})
export class MainModule { }
