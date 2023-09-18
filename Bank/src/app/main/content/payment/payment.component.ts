import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {LoginService} from '../../../_service/login.service';
import {PaymentService} from '../../../_service/paymentService';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  invalidNumber = false;
  invalidCVC = false;

  constructor(private router: Router, private loginService: LoginService, private paymentService: PaymentService) {
  }


  amount: any;
  payment = {
    transactionID: '',
    card: '',
    nic: '',
    amount: '',
    email: '',
    cardType: '',
    cvc: '',
    expirationYear: '',
    expirationMonth: ''
  };


  ngOnInit(): void {
    this.payment.nic = this.loginService.payment.nic;
    this.payment.email = this.loginService.payment.email;
    this.payment.amount = this.loginService.payment.amount;
    this.payment.cardType = this.loginService.payment.cardType;
    this.amount = this.loginService.payment.amount;
  }

  // tslint:disable-next-line:typedef
  onSubmit() {
    this.paymentService.addPayment(this.payment).subscribe((payment) => {
      console.log(payment);
      window.location.href = 'http://localhost:4200/main/buyer/pay_success?payId=' + payment.transactionID;
    })
  }

  chkCardNumber() {
    let CARD_REGEX = /^[0-9]{4}[ ][0-9]{4}[ ][0-9]{4}[ ][0-9]{4}$/;
    if (this.payment.card.length === 4) {
      this.payment.card += ' ';
    } else if (this.payment.card.length === 9) {
      this.payment.card += ' ';
    } else if (this.payment.card.length === 14) {
      this.payment.card += ' ';
    }
    if (CARD_REGEX.test(this.payment.card)) {
      this.invalidNumber = false;
    } else {
      this.invalidNumber = true;
    }
  }

  chkCVC() {
    let CVC_REGEX = /^[0-9]{3}$/;
    if (CVC_REGEX.test(this.payment.cvc)) {
      this.invalidCVC = false;
    } else {
      this.invalidCVC = true;
    }
  }
}
