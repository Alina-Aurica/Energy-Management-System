import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {Sensor} from "../../models/monitoring/Sensor";

@Injectable({
  providedIn: 'root'
})
export class SensorService {
  baseURL:string="http://localhost:8083/sensor";
  ownerDataStream:any;

  constructor(private httpClient:HttpClient) {
    this.ownerDataStream=new BehaviorSubject<any>(null);
  }

  getAllSensorByDeviceIDAndTimestamp(deviceID: any, timestamp: any): Observable<Sensor[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<Sensor[]>(this.baseURL + "/findAllByDeviceIDAndTimestamp/" + deviceID + "/" + timestamp, {headers: header});
  }

}
