import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { MainComponent } from './main/main.component';
import { ConfirmComponent } from './main/content/confirm/confirm.component';
import {FormsModule} from "@angular/forms";
import { CardTypeComponent } from './main/content/card-type/card-type.component';
import { PaymentComponent } from './main/content/payment/payment.component';
import {RouterModule} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    MainComponent,
    ConfirmComponent,
    CardTypeComponent,
    PaymentComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
      RouterModule,
      HttpClientModule,
      FormsModule,

    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
