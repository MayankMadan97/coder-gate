import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { GithubLoginComponent } from './github-login.component';

const routes: Routes = [
  { path: '', component: GithubLoginComponent },
  { path: 'github-callback', component: GithubLoginComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    GithubLoginComponent
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
