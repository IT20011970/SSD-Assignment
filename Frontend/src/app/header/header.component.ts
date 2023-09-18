import {Component, OnInit} from '@angular/core';
import {LoginService} from "../_service/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  topic;
  username;

  constructor(private loginService: LoginService, private router: Router) {
  }

  ngOnInit(): void {
    this.username = localStorage.getItem('user') !== null ? JSON.parse(localStorage.getItem('user'))['name'] : ''
  }

  accLogout() {
    this.loginService.accLogout();
    this.router.navigate(['/login'])
  }
}
