import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { FeaturesComponent } from './features/features.component';
import { GithubLoginComponent } from './github-login.component';
import { HomeComponent } from './home/home.component';
import { MainModule } from './main/main.module';
import { AuthGuard } from './shared/auth.guard';
import { OAuthModule } from 'angular-oauth2-oidc';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { ReactiveFormsModule } from '@angular/forms';
import { RepositoriesComponent } from './repositories/repositories.component';
import { MatDialogModule } from '@angular/material/dialog';

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
    FeaturesComponent,
    RepositoriesComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    MainModule,
    BrowserAnimationsModule,
    OAuthModule.forRoot({
      resourceServer: {
        allowedUrls: ['https://api.github.com'],
        sendAccessToken: true
      }
    }),
    ReactiveFormsModule,
    MatCheckboxModule,
    MatDialogModule
  ],
  exports: [
    GithubLoginComponent,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
