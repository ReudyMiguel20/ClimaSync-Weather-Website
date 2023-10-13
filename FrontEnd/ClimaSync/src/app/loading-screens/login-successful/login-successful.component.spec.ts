import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginSuccessfulComponent } from './login-successful.component';

describe('LoginSuccessfulComponent', () => {
  let component: LoginSuccessfulComponent;
  let fixture: ComponentFixture<LoginSuccessfulComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LoginSuccessfulComponent]
    });
    fixture = TestBed.createComponent(LoginSuccessfulComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
