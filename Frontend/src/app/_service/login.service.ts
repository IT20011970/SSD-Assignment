// @ts-nocheck
import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  accUser(email): Observable<any> {
    return this.http.get<any>(environment.backend_user_service + "/user/user/"+email);
  }

  loginGoogle(user): Observable<any> {
    return this.http.post<any>(environment.backend_user_service + "/user/login_google", user);
  }

  accLogin(user): Observable<any> {
    return this.http.post<any>(environment.backend_user_service + "/user/login", user);
  }

  signUp(user): Observable<any> {
    return this.http.post<any>(environment.backend_user_service + "/user/signUp", user);
  }

  accLogout() {
    // this.navBarService.logged = false;
    localStorage.clear();
  }
}
