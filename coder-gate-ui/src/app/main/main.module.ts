import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSliderModule } from '@angular/material/slider';
import { MatTableModule } from '@angular/material/table';
import { RouterModule, Routes } from '@angular/router';
import { HighchartsChartModule } from 'highcharts-angular';
import { AuthGuard } from '../shared/auth.guard';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NavBarComponent } from './navbar/navbar.component';
import { MatSelectModule } from '@angular/material/select';



import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDialogModule } from '@angular/material/dialog';
import { InsightsComponent } from '../insight/insight.component';
import { OnSubmitAlert } from './dashboard/dashboard.component';
const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
  { path: 'insights/:selectedRepo/:repoId', component: InsightsComponent, canActivate: [AuthGuard] }
];

@NgModule({
  declarations: [
    DashboardComponent,
    InsightsComponent,
    NavBarComponent,
    OnSubmitAlert
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
