import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationSuccessfulComponent } from './registration-successful.component';

describe('RegistrationSuccessfulComponent', () => {
  let component: RegistrationSuccessfulComponent;
  let fixture: ComponentFixture<RegistrationSuccessfulComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegistrationSuccessfulComponent]
    });
    fixture = TestBed.createComponent(RegistrationSuccessfulComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
