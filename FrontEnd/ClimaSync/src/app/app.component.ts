import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ClimaSync';

  images = [
    {
      imageSrc:
        '/assets/slideshow-photos/img4.jpg',
      imageAlt: 'weather1',
    },
    {
      imageSrc:
        '/assets/slideshow-photos/img2.jpeg',
      imageAlt: 'weather2',
    },
    {
      imageSrc:
        '/assets/slideshow-photos/img3.jpeg',
      imageAlt: 'weather3',
    },
    {
      imageSrc:
        '/assets/slideshow-photos/img1.jpeg',
      imageAlt: 'weather4',
    },
  ]
}
