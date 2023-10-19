// @ts-nocheck
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})
export class TokenTimeoutService {
  constructor(private loginService: LoginService, private router: Router) {}

  public checkTimeout(): boolean {
    const loggedTime = new Date(
      JSON.parse(localStorage.getItem('user')).loggedTime
    );

    const twentyMinutesFromLoggedTime = new Date(
      loggedTime.getTime() + 1 * 60000
    );

    const currentTime = new Date();

    console.log(loggedTime);
    console.log(twentyMinutesFromLoggedTime);
    console.log(twentyMinutesFromLoggedTime < currentTime);

    if (twentyMinutesFromLoggedTime < currentTime) {
      this.loginService.accLogout();
      this.router.navigate(['/login']);
      return true;
    } else {
      return false;
    }
  }
}
