import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user/User";
import {UserService} from "../../services/user/user.service";
//import {FormBuilder} from "@angular/forms";
import {DeviceService} from "../../services/device/device.service";
import {Device} from "../../models/device/Device";
import {Router} from "@angular/router";

@Component({
  selector: 'app-crud-client-page',
  templateUrl: './crud-client-page.component.html',
  styleUrls: ['./crud-client-page.component.css']
})
export class CrudClientPageComponent implements OnInit{

  userList: User[] = []
  user: User = new User()
  userAux: User = new User()
  typeOfSearchList = ["search by First Name", "search by Last Name", "search by Email", "search by Role"]
  keyword: any;
  typeOfSearch: any;
  constructor(private userService:UserService,
              private deviceService:DeviceService,
              private router: Router
              //private formBuilder:FormBuilder
  ) {}

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe(
      (result: User[]) => {
        console.log(result);
        this.userList = result;
      },
      (_error: Error) => {
        console.log("error");
      }
    );
  }

  search(): void {
    if(this.typeOfSearch == "search by First Name"){
      this.userService.getAllUsersByFirstName(this.keyword).subscribe(
        (result: User[]) => {
          console.log(result);
          this.userList = result;
        },
        (_error: Error) => {
          console.log("error");
        }
      )
    }

    if(this.typeOfSearch == "search by Last Name"){
      this.userService.getAllUsersByLastName(this.keyword).subscribe(
        (result: User[]) => {
          console.log(result);
          this.userList = result;
        },
        (_error: Error) => {
          console.log("error");
        }
      )
    }

    if(this.typeOfSearch == "search by Email"){
      this.userService.getAllUsersByEmail(this.keyword).subscribe(
        (result: User[]) => {
          console.log(result);
          this.userList = result;
        },
        (_error: Error) => {
          console.log("error");
        }
      )
    }

    if(this.typeOfSearch == "search by Role"){
      this.userService.getAllUsersByRole(this.keyword).subscribe(
        (result: User[]) => {
          console.log(result);
          this.userList = result;
        },
        (_error: Error) => {
          console.log("error");
        }
      )
    }
  }

  updateEmail(): void {
    console.log(this.user)
    this.userService.updateUserByEmail(this.user.firstName, this.user.lastName, this.user.email).subscribe(
      (result: User) => {
        console.log(result);
        this.userAux = result;
        console.log(this.userAux);
        alert("Update successfully");
      },
      (_error: Error) => {
        console.log("error");
      }
    )
  }

  insert(): void {
    console.log(this.user)
    this.userService.insertUser(this.user.firstName, this.user.lastName, this.user.email, this.user.password, this.user.role).subscribe(
      (result: User) => {
        console.log(result);
        this.userAux = result;
        console.log(this.userAux);
        alert("Insert successfully");
      },
      (_error: Error) => {
        console.log("error");
      }
    )
  }

  deleteByEmail(): void {
    console.log(this.user)
    this.userService.deleteUserByEmail(this.user.email).subscribe(
      (result: User) => {
        console.log(result);
        // this.userAux = result;
        // console.log(this.userAux);
        this.deviceService.deleteDeviceByClientID(result.id).subscribe(
          (resultDevice: Device) => {
            console.log(resultDevice)
          },
          (_error: Error) => {
            console.log("error device");
          }
        )
        alert("Delete successfully");
      },
      (_error: Error) => {
        console.log("error client");
      }
    )
  }

  logOut(): void {
    localStorage.clear()
    this.router.navigateByUrl("/firstPage")
  }

}
