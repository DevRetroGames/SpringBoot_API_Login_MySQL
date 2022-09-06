import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InputErrorContainerComponent } from './input-error-container.component';

describe('InputErrorContainerComponent', () => {
  let component: InputErrorContainerComponent;
  let fixture: ComponentFixture<InputErrorContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InputErrorContainerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InputErrorContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
