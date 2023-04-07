import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/shared/auth.service';
import { UserService } from 'src/app/user.service';

@Component({
    selector: 'app-navigator',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.css']
})
export class NavBarComponent implements OnInit {

    public showUserMenu = false;
    user : any;

    constructor(private authService: AuthService,private userService: UserService) {

    }
    ngOnInit(): void {
    const userString = localStorage.getItem("user");
    if(userString){
      this.user = JSON.parse(userString);
    }

    }

    logout() {
        localStorage.clear();
        this.authService.logout();
    }
}