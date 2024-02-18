import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LogInService{
  ownerDataStream:any;

  constructor(private httpClient:HttpClient) {
    this.ownerDataStream = new BehaviorSubject<any>(null);
  }

  public login(email: any, password: any): any {
    let credentials = { email: email, password: password };
    return this.httpClient.post('http://localhost:8082/user/login',
      JSON.stringify(credentials), { headers: { 'Content-Type': 'application/json' }});
  }

  public register(firstName: any, lastName: any, email: any, password: any, role: any): any
  {
    let credentials = {firstName: firstName, lastName: lastName, email: email, password: password, role:role}
    return this.httpClient.post('http://localhost:8082/user/register',
      JSON.stringify(credentials), {headers:{'Content-Type':'application/json'}, observe:'response'});
  }

}
