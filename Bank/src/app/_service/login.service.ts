import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  payment = {
    nic: '',
    amount: '',
    email: '',
    cardType: '',
    cvn: '',
    expirationYear: '',
    expirationMonth: '',
  };
  constructor() { }




}
