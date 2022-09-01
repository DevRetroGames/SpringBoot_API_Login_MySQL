import { Component, OnInit , Input } from '@angular/core';
import { ILoginInput } from './ILoginInput'

@Component({
  selector: 'app-login-input',
  templateUrl: './login-input.component.html',
  styleUrls: ['./login-input.component.css']
})
export class LoginInputComponent implements OnInit {

  @Input() data!: ILoginInput ;

  constructor() {
  }

  ngOnInit(): void {
  }

}
