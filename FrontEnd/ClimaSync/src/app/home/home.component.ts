import {Component, ElementRef, Renderer2 } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(private renderer: Renderer2,
              private elementRef: ElementRef) {
  }

  firstName = localStorage.getItem('first_name');

  images = [
    {
      imageSrc:
        '/assets/slideshow-photos/img4.jpg',
      imageAlt: 'weather1',
      imageCaption: 'tst'
    },
    {
      imageSrc:
        '/assets/slideshow-photos/img2.jpeg',
      imageAlt: 'weather2',
      imageCaption: 'tsteeee'
    },
    {
      imageSrc:
        '/assets/slideshow-photos/img3.jpeg',
      imageAlt: 'weather3',
      imageCaption: 'tstqweqweqwewq'
    },
    {
      imageSrc:
        '/assets/slideshow-photos/img1.jpeg',
      imageAlt: 'weather4',
      imageCaption: 'tsaaaaaaat'
    },
  ]

  userLoggedIn(): boolean {
    const token = localStorage.getItem('token');

    return !!token;
  }

  scrollToRegister() {
    const registerElement = this.elementRef.nativeElement.querySelector('#register');

    if(registerElement) {
      registerElement.scrollIntoView({behavior: 'smooth'});
    }

  }


}
