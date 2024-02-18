import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {Observable} from "rxjs";
import jwt_decode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class AuthAdminService implements CanActivate{
  routeURL: String;

  constructor(private router: Router) {
    this.routeURL = router.url
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const user: any = localStorage.getItem("token")
    var tokenLoad: any;
    tokenLoad = jwt_decode(user)
    if(tokenLoad.role === "CLIENT"){
      return this.router.navigateByUrl("/firstPage")
    }
    else {
      console.log(this.routeURL)
      return true;
    }
  }
}
