import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { FeaturesComponent } from './features/features.component';
import { GithubLoginComponent } from './github-login.component';
import { HomeComponent } from './home/home.component';
import { MainModule } from './main/main.module';
import { AuthGuard } from './shared/auth.guard';
import { OAuthModule } from 'angular-oauth2-oidc';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'github-callback', component: GithubLoginComponent },
  { path: 'main', loadChildren: () => import('./main/main.module').then(a => a.MainModule), canActivate: [AuthGuard] }
];


@NgModule({
  declarations: [
    AppComponent,
    GithubLoginComponent,
    HomeComponent,
    FeaturesComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    MainModule,
    OAuthModule.forRoot({
      resourceServer: {
        allowedUrls: ['https://api.github.com'],
        sendAccessToken: true
      }
    })
  ],
  exports: [
    GithubLoginComponent,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
