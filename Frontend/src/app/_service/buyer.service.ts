import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class BuyerService {

  cart;

  constructor(private http: HttpClient) {
  }

  getItems(txt): Observable<any> {
    const headersToken = new HttpHeaders()
      .set('X-CSRF-TOKEN', JSON.parse(localStorage.getItem('user')).token) // Replace with your header name and value
      .set('USER', JSON.parse(localStorage.getItem('user')).email);
    return this.http.get<any>(environment.backend_url2 + "/buyer/getItems/" + txt,{ headers: headersToken });
  }

  getDeliveries(): Observable<any> {
    const headersToken = new HttpHeaders()
      .set('X-CSRF-TOKEN', JSON.parse(localStorage.getItem('user')).token) // Replace with your header name and value
      .set('USER', JSON.parse(localStorage.getItem('user')).email);
    return this.http.get<any>(environment.backend_url4 + "/delivery/getDeliveries",{ headers: headersToken });
  }

  addToCart(cart) {
    return this.http.post<any>(environment.backend_url2 + "/buyer/addToCart", cart);
  }

  getCart() {
    const headersToken = new HttpHeaders()
      .set('X-CSRF-TOKEN', JSON.parse(localStorage.getItem('user')).token) // Replace with your header name and value
      .set('USER', JSON.parse(localStorage.getItem('user')).email);
    return this.http.get<any>(environment.backend_url2 + "/buyer/getCart/" + JSON.parse(localStorage.getItem('user')).email,{ headers: headersToken });
  }

  addCart(cart) {
    return this.http.post<any>(environment.backend_url2 + "/buyer/addCart", cart)
  }

  removeItem(id) {
    return this.http.delete<any>(environment.backend_url2 + "/buyer/removeCartDetail/" + id)
  }
}
