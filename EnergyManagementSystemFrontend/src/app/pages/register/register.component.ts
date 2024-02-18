import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user/User";
import {ActivatedRoute, Router} from "@angular/router";
import {LogInService} from "../../services/user/logIn.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{
  user: User = new User();

  // @ts-ignore
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private logInService: LogInService
  ){}

  ngOnInit(): void {
  }

  register(){
    console.log(this.user)
    this.logInService.register(this.user.firstName, this.user.lastName, this.user.email, this.user.password, this.user.role).subscribe(
      (result: User) => {
        console.log(result)
        this.user = result
        console.log(this.user)
        alert("Register successfully")
        //this.router.navigateByUrl("/login")
      },
      (_error: Error) => {
        alert("Some fields are null or contain invalid data. Please, rewrite them.")
      }
    )
  }

}
