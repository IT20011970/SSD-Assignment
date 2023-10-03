import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  getUser(){
    if (this.router.url.includes('farmer')) {
      return 'farmer'
    } else if (this.router.url.includes('buyer')) {
      return 'buyer'
    }
  }

}
