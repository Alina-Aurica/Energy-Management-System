import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {Device} from "../../models/device/Device";

@Injectable({
  providedIn: 'root'
})
export class DeviceService {
  baseURL:string="http://localhost:8081/device";
  ownerDataStream:any;

  constructor(private httpClient:HttpClient) {
    this.ownerDataStream=new BehaviorSubject<any>(null);
  }

  getAllDevices(): Observable<Device[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<Device[]>(this.baseURL + "/findAll", {headers: header});
  }

  getAllDevicesByName(name: any): Observable<Device[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<Device[]>(this.baseURL + "/findAllByName/" + name, {headers: header});
  }

  getAllDevicesByAddress(address: any): Observable<Device[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<Device[]>(this.baseURL + "/findAllByAddress/" + address, {headers: header});
  }

  getAllDevicesByMaximumHourlyEnergyConsumption(maximumHourlyEnergyConsumption: any): Observable<Device[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<Device[]>(this.baseURL + "/findAllByMaximumHourlyEnergyConsumption/" + maximumHourlyEnergyConsumption, {headers: header});
  }

  getAllDevicesByClientID(clientID: any): Observable<Device[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<Device[]>(this.baseURL + "/findAllByClientId/" + clientID, {headers: header});
  }

  insertDevice(name: any, description: any, address: any, maximumHourlyEnergyConsumption: any, clientID: any): any{
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    let credentials = {name: name, description: description, address: address, maximumHourlyEnergyConsumption: maximumHourlyEnergyConsumption, clientID: clientID};
    return this.httpClient.post(this.baseURL + "/insert",
      JSON.stringify(credentials) ,{headers: header});
  }

  updateDeviceByMaximumHourlyEnergyConsumption(name: any, maximumHourlyEnergyConsumption: any): any {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.put(this.baseURL + "/updateMaximumHourlyEnergyConsumption/" + name + "/" + maximumHourlyEnergyConsumption, null, {headers: header});
  }

  deleteDeviceByName(name: any): any {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.delete(this.baseURL + "/deleteByName/" + name,
      {headers: header});
  }

  deleteDeviceByClientID(clientID: any): any {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.delete(this.baseURL + "/deleteByClientID/" + clientID,
      {headers: header});
  }

  addClientID(nameDevice: any, clientID: any): any {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.put(this.baseURL + "/addClientDevice/" + nameDevice + "/" + clientID, null,
      {headers: header});
  }

}
