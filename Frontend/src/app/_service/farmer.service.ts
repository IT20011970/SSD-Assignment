import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { TokenTimeoutService } from './token-timeout.service';

@Injectable({
  providedIn: 'root',
})
export class FarmerService {
  item;

  constructor(
    private http: HttpClient,
    private tokenTimeoutService: TokenTimeoutService
  ) {}

  addItem(item): Observable<any> {
    const headersToken = new HttpHeaders()
      .set('X-CSRF-TOKEN', JSON.parse(localStorage.getItem('user')).token) // Replace with your header name and value
      .set('USER', JSON.parse(localStorage.getItem('user')).email);
    return this.http.post<any>(
      'http://localhost:8082' + '/farmer/addItem',
      item,
      { headers: headersToken }
    );
  }

  updateItem(item, itemId): Observable<any> {
    const headersToken = new HttpHeaders()
      .set('X-CSRF-TOKEN', JSON.parse(localStorage.getItem('user')).token) // Replace with your header name and value
      .set('USER', JSON.parse(localStorage.getItem('user')).email);
    return this.http.put<any>(
      'http://localhost:8082' + '/farmer/updateItem/' + itemId,
      item,
      { headers: headersToken }
    );
  }

  removeItem(itemId): Observable<any> {
    const headersToken = new HttpHeaders()
      .set('X-CSRF-TOKEN', JSON.parse(localStorage.getItem('user')).token) // Replace with your header name and value
      .set('USER', JSON.parse(localStorage.getItem('user')).email);
    return this.http.delete<any>(
      environment.backend_url3 + '/farmer/removeItem/' + itemId,
      { headers: headersToken }
    );
  }

  getItems(): Observable<any> {
    if (this.tokenTimeoutService.checkTimeout()) {
      return;
    }

    // Make the GET request with the custom headers
    const headersToken = new HttpHeaders()
      .set('X-CSRF-TOKEN', JSON.parse(localStorage.getItem('user')).token) // Replace with your header name and value
      .set('USER', JSON.parse(localStorage.getItem('user')).email);
    return this.http.get<any>(
      environment.backend_url3 +
        '/farmer/getItems/' +
        JSON.parse(localStorage.getItem('user')).email,
      { headers: headersToken }
    );
    // return this.http.get<any>(environment.backend_url3 + "/farmer/getItems/" + JSON.parse(localStorage.getItem('user')).email);
  }
  addChat(setItem: any): Observable<any> {
    console.log(setItem);
    const headersToken = new HttpHeaders()
      .set('X-CSRF-TOKEN', JSON.parse(localStorage.getItem('user')).token) // Replace with your header name and value
      .set('USER', JSON.parse(localStorage.getItem('user')).email);
    return this.http.post(
      environment.backend_url3 + '/farmer/addChat',
      setItem,
      { headers: headersToken }
    );
  }
  getAllChats(): Observable<any> {
    const headersToken = new HttpHeaders()
      .set('X-CSRF-TOKEN', JSON.parse(localStorage.getItem('user')).token) // Replace with your header name and value
      .set('USER', JSON.parse(localStorage.getItem('user')).email);
    return this.http.get<any>(environment.backend_url3 + '/farmer/getchat', {
      headers: headersToken,
    });
  }
}
