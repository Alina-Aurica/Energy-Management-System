import {Component, OnInit} from '@angular/core';
import {DeviceService} from "../../services/device/device.service";
import {Device} from "../../models/device/Device";
import jwt_decode from "jwt-decode";
import {Router} from "@angular/router";
import {WebSocketAPI} from "../../services/websocket/WebSocketAPI";

@Component({
  selector: 'app-client-page',
  templateUrl: './client-page.component.html',
  styleUrls: ['./client-page.component.css']
})
export class ClientPageComponent implements OnInit {
  deviceList: Device[] = []
  deviceID: any
  deviceIDString: any

  constructor(private deviceService:DeviceService,
              private router: Router,
              private webSocketAPI: WebSocketAPI
  ) {
    console.log("Suntem in constructorul de la ClientPageComponent!");
    let stompClient = this.webSocketAPI.connect();
    stompClient.connect({}, () => {
      console.log("Conexiune cu WebSocket");
      stompClient.subscribe('/topic/websocket/client', notifications => {
        console.log("Suntem in metoda din constructor");
        this.deviceID = notifications.body;
        this.deviceIDString = JSON.stringify(notifications.body);
        console.log("id-ul device-ului: " + this.deviceIDString)
        this.sendNotification(this.deviceID);
        //window.location.reload();
      })
    });
  }

  ngOnInit(): void {
    const user: any = localStorage.getItem("token")
    var tokenLoad: any;
    tokenLoad = jwt_decode(user)
    console.log(tokenLoad.clientID)
    this.deviceService.getAllDevicesByClientID(tokenLoad.clientID).subscribe(
      (result: Device[]) => {
        this.deviceList = result
        console.log(this.deviceList)
      },
      (_error: Error) => {
        console.log("error");
      }
    )
  }

  logOut(): void {
    localStorage.clear()
    this.router.navigateByUrl("/firstPage")
  }

  sendNotification(message: any): void {
    const user: any = localStorage.getItem("token")
    var tokenLoad: any;
    tokenLoad = jwt_decode(user)
    console.log(tokenLoad.clientID)
    this.deviceService.getAllDevicesByClientID(tokenLoad.clientID).subscribe(
      (result: Device[]) => {
        console.log("Suntem in metoda de sendNotification!");
        result.forEach(function(device: Device){
          console.log("Device id -- in sendNotification: " + device.id);
          console.log("Message -- in sendNotification: " + message);
          if(device.id === message){
            console.log("Suntem inainte de alert!");
            alert("Device-ul : " + device.id + " has exceeded its consumption level!")
          }
        })
      },
      (_error: Error) => {
        console.log("error");
      }
    )
  }

}
