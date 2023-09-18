import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class FarmerService {

  item;

  constructor(private http: HttpClient) {
  }

  addItem(item): Observable<any> {
    return this.http.post<any>('http://localhost:8082' + "/farmer/addItem", item);
  }

  updateItem(item, itemId): Observable<any> {
    return this.http.put<any>('http://localhost:8082' + "/farmer/updateItem/" + itemId, item);
  }

  removeItem(itemId): Observable<any> {
    return this.http.delete<any>(environment.backend_url3 + "/farmer/removeItem/" + itemId);
  }

  getItems(): Observable<any> {
    return this.http.get<any>(environment.backend_url3 + "/farmer/getItems/" + JSON.parse(localStorage.getItem('user')).email);
  }
  addChat(setItem: any): Observable<any>{
    console.log(setItem)
    return this.http.post(environment.backend_url3+ "/farmer/addChat", setItem);
  }
  getAllChats(): Observable<any>{
    return this.http.get<any>(environment.backend_url3 + "/farmer/getchat");
  }

}
