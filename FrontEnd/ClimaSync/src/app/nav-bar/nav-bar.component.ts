import {Component, EventEmitter, Output} from '@angular/core';

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

  scrollToRegister() {
    const registerElement = document.getElementById('register');

    if (registerElement) {
      registerElement.scrollIntoView({ behavior: 'smooth' });
    }
  }
}
