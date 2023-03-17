import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { RouterModule, Routes } from '@angular/router';
import { HighchartsChartModule } from 'highcharts-angular';
import { AuthGuard } from '../shared/auth.guard';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NavBarComponent } from './navbar/navbar.component';

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] }
];

@NgModule({
  declarations: [
    DashboardComponent,
    NavBarComponent
  ],
  imports: [
    CommonModule,
    MatTableModule,
    HighchartsChartModule,
    RouterModule.forChild(routes)
  ]
})
export class MainModule { }
