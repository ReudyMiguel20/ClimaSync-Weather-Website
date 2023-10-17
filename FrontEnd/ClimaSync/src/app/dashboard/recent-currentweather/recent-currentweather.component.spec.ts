import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecentCurrentweatherComponent } from './recent-currentweather.component';

describe('RecentCurrentweatherComponent', () => {
  let component: RecentCurrentweatherComponent;
  let fixture: ComponentFixture<RecentCurrentweatherComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RecentCurrentweatherComponent]
    });
    fixture = TestBed.createComponent(RecentCurrentweatherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
