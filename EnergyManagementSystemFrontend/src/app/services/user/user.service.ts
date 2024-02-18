import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {User} from "../../models/user/User";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseURL:string = "http://localhost:8082/user";
  ownerDataStream:any;

  constructor(private httpClient:HttpClient) {
    this.ownerDataStream = new BehaviorSubject<any>(null);
  }

  getUserByID(id: any): Observable<User>{
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<User>(this.baseURL + "/findById/" + id, {headers: header});

  }

  getUserByEmail(email: any): Observable<User>{
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<User>(this.baseURL + "/findByEmail/" + email, {headers: header});

  }

  getAllUsers(): Observable<User[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<User[]>(this.baseURL + "/findAll", {headers: header});
  }

  getAllUsersByFirstName(firstName: any): Observable<User[]>{
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<User[]>(this.baseURL + "/findAllByFirstName/" + firstName, {headers: header});
  }

  getAllUsersByLastName(lastName: any): Observable<User[]>{
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<User[]>(this.baseURL + "/findAllByLastName/" + lastName, {headers: header});
  }

  getAllUsersByEmail(email: any): Observable<User[]>{
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<User[]>(this.baseURL + "/findAllByEmail/" + email, {headers: header});
  }

  getAllUsersByRole(role: any): Observable<User[]>{
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<User[]>(this.baseURL + "/findAllByRole/" + role, {headers: header});
  }

  insertUser(firstName: any, lastName: any, email: any, password: any, role: any): any{
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    let credentials = {firstName: firstName, lastName: lastName, email: email, password: password, role: role};
    return this.httpClient.post(this.baseURL + "/insert",
      JSON.stringify(credentials) ,{headers: header});
  }

  updateUserByEmail(firstName: any, lastName: any, email: any): any {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.put(this.baseURL + "/updateEmail/" + firstName + "/" + lastName + "/" + email, null, {headers: header});
  }

  deleteUserByEmail(email: any): any {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.delete(this.baseURL + "/deleteByEmail/" + email,
      {headers: header});
  }

  getEmailsByRole(role: any): Observable<String[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<String[]>(this.baseURL + "/findAllEmailsByRole/" + role, {headers: header});
  }
}
