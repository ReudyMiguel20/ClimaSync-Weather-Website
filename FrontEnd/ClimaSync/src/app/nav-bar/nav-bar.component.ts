import { Component } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent {

  logout() {
    localStorage.clear();
    window.location.reload();
  }

  userLoggedIn(): boolean {
    const token = localStorage.getItem('token');
    return !!token;
  }
}
