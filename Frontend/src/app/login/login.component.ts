import { Component, OnInit } from '@angular/core';
import {LoginService} from "../_service/login.service";
import {NavbarService} from "../_service/navbar.service";
import {Router} from "@angular/router";
import { AppConstants } from '../common/app.constants';
import { ActivatedRoute } from '@angular/router';
import { GoogleApiService, UserInfo } from '../_service/google-api.service';
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

  userInfo?: UserInfo
  constructor(private loginService: LoginService,private route: ActivatedRoute, private navBarService: NavbarService, private router: Router,private readonly googleApi: GoogleApiService) {

  }
  googleURL = AppConstants.GOOGLE_AUTH_URL;
  login1(){
    // this.googleApi.initializeOAuthService().subscribe((user) => {
    //   console.log(user)
    // })
    this.googleApi.loginGoogle().subscribe((user)=>{

          localStorage.setItem('email', user.email);
          this.ngOnInit()
    }
    )
  }

  // loginWithGoogle(): void {
  //   this.oauthService.initLoginFlow();
  // }
  ngOnInit(): void {
      const userEmail=localStorage.getItem('email') !== null ? localStorage.getItem('email') : ''
      console.log(userEmail)
      if(userEmail){
        this.loginService.accUser(userEmail).subscribe((user) => {
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
      // Use the userEmail in your application logic

  }
  isLoggedIn(): boolean {
    return this.googleApi.isLoggedIn()
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
