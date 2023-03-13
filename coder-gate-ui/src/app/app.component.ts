import { Component, OnChanges, SimpleChanges } from '@angular/core';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnChanges {

  ngOnChanges(changes: SimpleChanges): void {

    this.isAuthenticated = AuthService.isAuthenticated;

  }


  public isAuthenticated = false;


}
