import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration-successful',
  templateUrl: './registration-successful.component.html',
  styleUrls: ['./registration-successful.component.css']
})
export class RegistrationSuccessfulComponent {

  constructor(private router: Router) {
  }

  ngOnInit() {
    setTimeout(() => {
      this.router.navigate(['']);
    }, 3000);
  }

}
