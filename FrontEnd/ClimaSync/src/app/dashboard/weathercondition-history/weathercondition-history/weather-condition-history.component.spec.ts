import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeatherConditionHistoryComponent } from './weather-condition-history.component';

describe('WeatherconditionHistoryComponent', () => {
  let component: WeatherConditionHistoryComponent;
  let fixture: ComponentFixture<WeatherConditionHistoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [WeatherConditionHistoryComponent]
    });
    fixture = TestBed.createComponent(WeatherConditionHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
