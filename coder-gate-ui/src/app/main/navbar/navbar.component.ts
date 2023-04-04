import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/shared/auth.service';
import { User } from 'src/app/user.interface';
import { UserService } from 'src/app/user.service';

@Component({
    selector: 'app-navigator',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.css']
})
export class NavBarComponent implements OnInit {
    user!: User;

    public showUserMenu = false;

    constructor(private authService: AuthService,private userService: UserService) {

    }
    ngOnInit(): void {
        this.user = this.userService.user;
        console.log('User data retrieved in NavBarComponent:', this.user);
    }

    logout() {
        this.authService.logout();
    }
}