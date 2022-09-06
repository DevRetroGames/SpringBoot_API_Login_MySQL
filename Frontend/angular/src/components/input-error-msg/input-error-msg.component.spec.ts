import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InputErrorMsgComponent } from './input-error-msg.component';

describe('InputErrorMsgComponent', () => {
  let component: InputErrorMsgComponent;
  let fixture: ComponentFixture<InputErrorMsgComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InputErrorMsgComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InputErrorMsgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
