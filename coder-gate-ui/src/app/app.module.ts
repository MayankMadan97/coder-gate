import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { FeaturesComponent } from './features/features.component';
import { GithubLoginComponent } from './github-login.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'github-callback', component: GithubLoginComponent },
  { path: 'dashboard', component: DashboardComponent }
];


@NgModule({
  declarations: [
    AppComponent,
    GithubLoginComponent,
    DashboardComponent,
    FeaturesComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
  ],
  exports: [
    GithubLoginComponent,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
