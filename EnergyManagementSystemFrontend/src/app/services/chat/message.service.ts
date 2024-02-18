import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {ChatMessage} from "../../models/chat/chatMessage";

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  baseURL:string = "http://localhost:8085/chatMessage";
  ownerDataStream:any;

  constructor(private httpClient:HttpClient) {
    this.ownerDataStream = new BehaviorSubject<any>(null);
  }

  getAllConversation(chatMessage: any): Observable<ChatMessage[]>{
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<ChatMessage[]>(this.baseURL + "/findAllMessages/" + chatMessage.sender + "/" + chatMessage.receiver, {headers: header});
  }

  updateChatMessage(chatMessage: any): Observable<ChatMessage>{
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    let credentials = {content: chatMessage.content, sender: chatMessage.sender, receiver: chatMessage.receiver, timestamp: chatMessage.timestamp, seen: chatMessage.seen}
    return this.httpClient.put<ChatMessage>(this.baseURL + "/updateMessage", JSON.stringify(credentials) ,{headers: header});
  }
}
