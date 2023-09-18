import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../../_service/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-card-type',
  templateUrl: './card-type.component.html',
  styleUrls: ['./card-type.component.css']
})
export class CardTypeComponent implements OnInit {

  constructor(private loginService: LoginService, private router: Router) { }

  ngOnInit(): void {

  }

  visa() {
    this.loginService.payment.cardType = 'Visa';
    this.router.navigate(['main/payment']);
  }

  master() {
    this.loginService.payment.cardType = 'Master'
    this.router.navigate(['main/payment']);
  }

  amex() {
    this.loginService.payment.cardType = 'Amex'
    this.router.navigate(['main/payment']);
  }
}
