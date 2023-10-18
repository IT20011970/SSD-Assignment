// @ts-nocheck
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {AuthConfig, OAuthService} from 'angular-oauth2-oidc';
import {from, Observable, Subject} from 'rxjs';
import {switchMap} from "rxjs/operators";

const authCodeFlowConfig: AuthConfig = {
    // Url of the Identity Provider
    issuer: 'https://accounts.google.com',

    // strict discovery document disallows urls which not start with issuers url
    strictDiscoveryDocumentValidation: false,

    // URL of the SPA to redirect the user to after login
    redirectUri: window.location.origin,

    // The SPA's id. The SPA is registerd with this id at the auth-server
    // clientId: 'server.code',
    clientId: '748008346564-hon84taook6557panurkiatb9ff8untf.apps.googleusercontent.com',

    // set the scope for the permissions the client should request
    scope: 'openid profile email https://www.googleapis.com/auth/gmail.readonly',

    showDebugInformation: true,
};

export interface UserInfo {
    info: {
        sub: string
        email: string,
        name: string,
        picture: string
    }
}

@Injectable({
    providedIn: 'root'
})
export class GoogleApiService {

    gmail = 'https://gmail.googleapis.com'

    userProfileSubject = new Subject<UserInfo>()

    constructor(private readonly oAuthService: OAuthService, private readonly httpClient: HttpClient) {

    }

    loginGoogle(): Observable<any> {
        // configure oauth2 service
        this.oAuthService.configure(authCodeFlowConfig);

        // manually configure a logout url, because Google's discovery document does not provide it
        this.oAuthService.logoutUrl = "https://www.google.com/accounts/Logout";

        // loading the discovery document from Google, which contains all relevant URLs for
        // the OAuth flow, e.g. login URL
        return from(this.oAuthService.loadDiscoveryDocument()).pipe(
            switchMap(() => {
                // This method just tries to parse the token(s) within the URL when
                // the auth-server redirects the user back to the web-app
                // It doesn't send the user to the login page
                return from(this.oAuthService.tryLoginImplicitFlow()).pipe(
                    switchMap(() => {
                        // when not logged in, redirect to Google for login
                        // else load user profile
                        if (!this.oAuthService.hasValidAccessToken()) {
                            this.oAuthService.initLoginFlow();
                            return null; // Return null if not logged in
                        } else {
                            return from(this.oAuthService.loadUserProfile()); // Return the user profile if logged in
                        }
                    })
                );
            })
        );
    }

    emails(userId: string): Observable<any> {
        return this.httpClient.get(`${this.gmail}/gmail/v1/users/${userId}/messages`, {headers: this.authHeader()})
    }

    getMail(userId: string, mailId: string): Observable<any> {
        return this.httpClient.get(`${this.gmail}/gmail/v1/users/${userId}/messages/${mailId}`, {headers: this.authHeader()})
    }

    isLoggedIn(): boolean {
        return this.oAuthService.hasValidAccessToken()
    }

    signOut() {
        this.oAuthService.logOut()
    }

    private authHeader(): HttpHeaders {
        return new HttpHeaders({
            'Authorization': `Bearer ${this.oAuthService.getAccessToken()}`
        })
    }
}

// import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { Injectable } from '@angular/core';
// import { AuthConfig, OAuthService } from 'angular-oauth2-oidc';
// import {from, Observable, Subject} from 'rxjs';
// import {switchMap} from "rxjs/operators";
//
// const authCodeFlowConfig: AuthConfig = {
//   // Url of the Identity Provider
//   issuer: 'https://accounts.google.com',
//
//   // strict discovery document disallows urls which not start with issuers url
//   strictDiscoveryDocumentValidation: false,
//
//   // URL of the SPA to redirect the user to after login
//   redirectUri: window.location.origin,
//
//   // The SPA's id. The SPA is registerd with this id at the auth-server
//   // clientId: 'server.code',
//   clientId: '748008346564-hon84taook6557panurkiatb9ff8untf.apps.googleusercontent.com',
//
//   // set the scope for the permissions the client should request
//   scope: 'openid profile email https://www.googleapis.com/auth/gmail.readonly',
//
//   showDebugInformation: true,
// };
//
// export interface UserInfo {
//   info: {
//     sub: string
//     email: string,
//     name: string,
//     picture: string
//   }
// }
//
// @Injectable({
//   providedIn: 'root'
// })
// export class GoogleApiService {
//
//   gmail = 'https://gmail.googleapis.com'
//
//   userProfileSubject = new Subject<UserInfo>()
//
//   constructor(private readonly oAuthService: OAuthService, private readonly httpClient: HttpClient) {}
//
//     initializeOAuthService(): Observable<any> {
//         // configure oauth2 service
//         this.oAuthService.configure(authCodeFlowConfig);
//
//         // manually configure a logout url, because Google's discovery document does not provide it
//         this.oAuthService.logoutUrl = 'https://www.google.com/accounts/Logout';
//
//         // loading the discovery document from Google, which contains all relevant URLs for
//         // the OAuth flow, e.g. login URL
//         return from(this.oAuthService.loadDiscoveryDocument()).pipe(
//             switchMap(() => {
//                 // This method just tries to parse the token(s) within the URL when
//                 // the auth-server redirects the user back to the web-app
//                 // It doesn't send the user to the login page
//                 return from(this.oAuthService.tryLoginImplicitFlow()).pipe(
//                     switchMap(() => {
//                         // when not logged in, redirect to Google for login
//                         // else load user profile
//                         if (!this.oAuthService.hasValidAccessToken()) {
//                             this.oAuthService.initLoginFlow();
//                             return null;
//                         } else {
//                             return from(this.oAuthService.loadUserProfile());
//                         }
//                     })
//                 );
//             })
//         );
//     }
//
//   emails(userId: string): Observable<any> {
//     return this.httpClient.get(`${this.gmail}/gmail/v1/users/${userId}/messages`, { headers: this.authHeader() })
//   }
//
//   getMail(userId: string, mailId: string): Observable<any> {
//     return this.httpClient.get(`${this.gmail}/gmail/v1/users/${userId}/messages/${mailId}`, { headers: this.authHeader() })
//   }
//
//   isLoggedIn(): boolean {
//     return this.oAuthService.hasValidAccessToken()
//   }
//
//   signOut() {
//     this.oAuthService.logOut()
//   }
//
//   private authHeader() : HttpHeaders {
//     return new HttpHeaders ({
//       'Authorization': `Bearer ${this.oAuthService.getAccessToken()}`
//     })
//   }
// }
