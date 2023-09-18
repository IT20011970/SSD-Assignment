import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {LoginService} from '../../../_service/login.service';

@Component({
  selector: 'app-confirm',
  templateUrl: './confirm.component.html',
  styleUrls: ['./confirm.component.css']
})
export class ConfirmComponent implements OnInit {

  constructor(private router: Router, private loginService: LoginService) { }

  user = {
    nic: '',
    email: '',
    amount: ''
  };

  ngOnInit(): void {
    this.user.nic = this.loginService.payment.nic;
    this.user.email = this.loginService.payment.email;
    this.user.amount = this.loginService.payment.amount;
 }

  // tslint:disable-next-line:typedef
  onSubmit() {
    this.loginService.payment.nic = this.user.nic;
    this.loginService.payment.email = this.user.email;
    this.loginService.payment.amount = this.user.amount;
    this.router.navigate(['main/card']);
  }

}
