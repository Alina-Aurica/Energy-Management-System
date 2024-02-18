import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user/User";
import {ActivatedRoute, Router} from "@angular/router";
import jwt_decode from "jwt-decode";
import {LogInService} from "../../services/user/logIn.service";

@Component({
  selector: 'app-log-in-page',
  templateUrl: './log-in-page.component.html',
  styleUrls: ['./log-in-page.component.css']
})
export class LogInPageComponent implements OnInit {
  user: User = new User();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private logInService: LogInService
  ){}

  ngOnInit(){

  }

  login() {
    console.log(this.user);
    this.logInService.login(this.user.email, this.user.password).subscribe(
      (result: any) => {
        console.log(result.token);
        localStorage.setItem("token", result.token);
        alert("Login successfully");
        var tokenLoad: any;
        tokenLoad = jwt_decode(result.token)
        if (tokenLoad.role === "ADMIN") {
          console.log("admin");
          this.router.navigateByUrl("/adminPage");
        } else {
          if (tokenLoad.role === "CLIENT") {
            console.log("client");
            this.router.navigateByUrl("/clientPage");
          }
        }
      },
      (_error: Error) => {
        alert("Email and/or password are incorrect. Please, rewrite them.");
      }
    );
  }


}
