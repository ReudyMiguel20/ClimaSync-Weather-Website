import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-sidebar-options',
  templateUrl: './sidebar-options.component.html',
  styleUrls: ['./sidebar-options.component.css']
})
export class SidebarOptionsComponent {

  constructor(private router: Router) {

  }

  navigateToWeatherCondition() {
    this.router.navigate(['/dashboard']);
  }

  navigateToUserInformation() {
    this.router.navigate(['/user-information']);
  }

}
