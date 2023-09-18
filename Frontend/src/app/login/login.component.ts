import { Component, OnInit } from '@angular/core';
import {LoginService} from "../_service/login.service";
import {NavbarService} from "../_service/navbar.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user = {
    email: '',
    password: ''
  };
  logged = true;

  constructor(private loginService: LoginService, private navBarService: NavbarService, private router: Router) {
  }

  ngOnInit(): void {
  }

  onSubmit() {
    this.loginService.accLogin(this.user).subscribe((user) => {
      localStorage.setItem('user', JSON.stringify(user));
      if (user !== null && user['accountType'] === 'F') {
        this.router.navigate(['/main/farmer/view_items'])
      } else if (user['accountType'] === 'B') {
        this.router.navigate(['/main/buyer/view_items']);
      } else {
        this.logged = false;
      }
    }, (err) => {
      this.logged = false;
    })
  }

}
