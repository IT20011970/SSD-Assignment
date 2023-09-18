import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {LoginService} from "../_service/login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  param1: any;
  param2: any;

  constructor(private router: Router, private loginService: LoginService, activatedRoute: ActivatedRoute) {
    console.log('Called Constructor');
    activatedRoute.queryParams.subscribe(params => {
      this.user.amount = params['total'];
    })
  }

  user = {
    nic: '',
    email: '',
    amount: ''
  };

  ngOnInit(): void {
  }

  // tslint:disable-next-line:typedef
  onSubmit() {
    localStorage.clear();
    console.log(this.user);
    this.loginService.payment.nic = this.user.nic;
    this.loginService.payment.email = this.user.email;
    this.loginService.payment.amount = this.user.amount;
    this.router.navigate(['main/confirm']);
  }

}
