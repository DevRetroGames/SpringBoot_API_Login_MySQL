import { Component, Host, Input, OnInit } from '@angular/core';
import { AbstractControl } from '@angular/forms';

import { InputErrorContainerComponent } from '@components/input-error-container/input-error-container.component'

import { ErrorsDictionary } from './errorDictionary'

@Component({
  selector: 'app-input-error-msg',
  templateUrl: './input-error-msg.component.html',
  styleUrls: ['./input-error-msg.component.css']
})
export class InputErrorMsgComponent implements OnInit {

  @Input( 'formErrorCode' ) errorCode!: string ;

  control!: AbstractControl | null ;

  get errorMsg(): string {
    return ErrorsDictionary[ this.errorCode ] ;
  }
  

  constructor( @Host() private _errorContainer: InputErrorContainerComponent ) { }

  ngOnInit(): void {
    this.control = this._errorContainer.controll ;
  }

}
