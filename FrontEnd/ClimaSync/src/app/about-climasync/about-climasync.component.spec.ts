import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AboutClimasyncComponent } from './about-climasync.component';

describe('AboutClimasyncComponent', () => {
  let component: AboutClimasyncComponent;
  let fixture: ComponentFixture<AboutClimasyncComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AboutClimasyncComponent]
    });
    fixture = TestBed.createComponent(AboutClimasyncComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
