import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})

export class PaymentService {
  constructor(private http: HttpClient) { }
  addPayment(cart: any): Observable<any>{
    console.log(cart);
    return this.http.post(environment.backend_url + '/payment/addPayment', cart);
  }


}
