import { Component , Input } from '@angular/core';
import { AbstractControl } from '@angular/forms';

@Component({
  selector: 'app-input-error-container',
  templateUrl: './input-error-container.component.html',
  styleUrls: ['./input-error-container.component.css']
})
export class InputErrorContainerComponent {

  @Input( 'formControll' ) controll! : AbstractControl | null ;

  constructor() {}

}
