import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {MainComponent} from "./main/main.component";
import {ConfirmComponent} from "./main/content/confirm/confirm.component";
import {CardTypeComponent} from "./main/content/card-type/card-type.component";
import {PaymentComponent} from "./main/content/payment/payment.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'main',
    component: MainComponent,
    children:
      [
      {
        path: 'confirm',
        component: ConfirmComponent
      },
        {
          path: 'card',
          component: CardTypeComponent
        },
        {
          path: 'payment',
          component: PaymentComponent
        }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
