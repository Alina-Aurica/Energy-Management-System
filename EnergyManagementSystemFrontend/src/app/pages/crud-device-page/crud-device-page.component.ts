import {Component, OnInit} from '@angular/core';
import {Device} from "../../models/device/Device";
import {UserService} from "../../services/user/user.service";
import {DeviceService} from "../../services/device/device.service";
import {User} from "../../models/user/User";
import {Router} from "@angular/router";

@Component({
  selector: 'app-crud-device-page',
  templateUrl: './crud-device-page.component.html',
  styleUrls: ['./crud-device-page.component.css']
})
export class CrudDevicePageComponent implements OnInit{
  deviceList: Device[] = []
  device: Device = new Device()
  deviceAux: Device = new Device()
  typeOfSearchList = ["search by name", "search by address", "search by maximum hourly energy consumption", "search by client ID"]
  keyword: any
  typeOfSearch: any
  clientEmail: any
  clientEmailList: any

  constructor(private deviceService:DeviceService,
              private userService:UserService,
              private router: Router
  ) {}

  ngOnInit(): void {
    this.deviceService.getAllDevices().subscribe(
      (result: Device[]) => {
        console.log(result);
        this.deviceList = result;
      },
      (_error: Error) => {
        console.log("error");
      }
    );

    this.userService.getEmailsByRole("CLIENT").subscribe(
      (result: String[]) => {
        console.log(result);
        this.clientEmailList = result;
      },
      (_error: Error) => {
        console.log("error");
      }

    )
  }

  search(): void {
    if(this.typeOfSearch == "search by name"){
      this.deviceService.getAllDevicesByName(this.keyword).subscribe(
        (result: Device[]) => {
          console.log(result);
          this.deviceList = result;
        },
        (_error: Error) => {
          console.log("error");
        }
      )
    }

    if(this.typeOfSearch == "search by address"){
      this.deviceService.getAllDevicesByAddress(this.keyword).subscribe(
        (result: Device[]) => {
          console.log(result);
          this.deviceList = result;
        },
        (_error: Error) => {
          console.log("error");
        }
      )
    }

    if(this.typeOfSearch == "search by maximum hourly energy consumption"){
      this.deviceService.getAllDevicesByMaximumHourlyEnergyConsumption(this.keyword).subscribe(
        (result: Device[]) => {
          console.log(result);
          this.deviceList = result;
        },
        (_error: Error) => {
          console.log("error");
        }
      )
    }

    if(this.typeOfSearch == "search by client ID"){
      this.deviceService.getAllDevicesByClientID(this.keyword).subscribe(
        (result: Device[]) => {
          console.log(result);
          this.deviceList = result;
        },
        (_error: Error) => {
          console.log("error");
        }
      )
    }
  }

  updateName(): void {
    console.log(this.device)
    this.deviceService.updateDeviceByMaximumHourlyEnergyConsumption(this.device.name, this.device.maximumHourlyEnergyConsumption).subscribe(
      (result: Device) => {
        console.log(result);
        this.deviceAux = result;
        console.log(this.deviceAux);
        alert("Update successfully");
      },
      (_error: Error) => {
        console.log("error");
      }
    )
  }

  insert(): void {
    console.log(this.device)
    this.userService.getUserByEmail(this.clientEmail).subscribe(
      (result:User) => {
        this.deviceService.insertDevice(this.device.name, this.device.description, this.device.address, this.device.maximumHourlyEnergyConsumption, result.id).subscribe(
          (result: Device) => {
            console.log(result);
            this.deviceAux = result;
            console.log(this.deviceAux);
            alert("Insert successfully");
          },
          (_error: Error) => {
            console.log("error device");
          }
        )
      },
      (_error: Error) => {
        console.log("error client");
      }
    )
  }

  deleteByName(): void {
    console.log(this.device)
    this.deviceService.deleteDeviceByName(this.device.name).subscribe(
      (result: Device) => {
        console.log(result);
        this.deviceAux = result;
        console.log(this.deviceAux);
        alert("Delete successfully");
      },
      (_error: Error) => {
        console.log("error");
      }
    )
  }

  asocieteClientDevice(): void {
    console.log(this.device)
    this.userService.getUserByEmail(this.clientEmail).subscribe(
      (result:User) => {
        this.deviceService.addClientID(this.device.name, result.id).subscribe(
          (resultDevice: Device) => {
            console.log(resultDevice)
            alert("Asociate correctly");
          },
          (_error: Error) => {
            console.log("error with device");
          }
        )
      },
      (_error: Error) => {
        console.log("error with client");
      }
    )
  }

  logOut(): void{
    localStorage.clear()
    this.router.navigateByUrl("/firstPage")
  }
}
