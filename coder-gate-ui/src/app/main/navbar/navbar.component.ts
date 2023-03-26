import { Component } from '@angular/core';
import { AuthService } from 'src/app/shared/auth.service';

@Component({
    selector: 'app-navigator',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.css']
})
export class NavBarComponent {
    public showUserMenu = false;
    user = JSON.parse(localStorage.getItem('user') || 'null');

    constructor(private authService: AuthService) {

    }

    logout() {
        this.authService.logout();
    }
}