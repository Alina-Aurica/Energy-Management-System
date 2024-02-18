import * as SockJS from 'sockjs-client';
import * as Stomp from "stompjs";
import {Injectable} from "@angular/core";

@Injectable()
export class WebSocketChat {
  webSocketEndPointChat: string = 'http://localhost:8085/wsChat';
  webSocketEndPointNotification: string = 'http://localhost:8085/wsChatNotification';
  constructor(){

  }

  public connect() {
    const socket = new SockJS(this.webSocketEndPointChat);
    let stompClient: Stomp.Client;
    stompClient = Stomp.over(socket);
    return stompClient
  }

  public connectNotification() {
    const socket = new SockJS(this.webSocketEndPointNotification);
    let stompClient: Stomp.Client;
    stompClient = Stomp.over(socket);
    return stompClient
  }

  // public disconnect() {
  //   if (this.stompClient !== null) {
  //     this.stompClient.disconnect();
  //   }
  // }

  // public sendMessageToAdmin(message: any) {
  //   if(this.stompClient)
  //     this.stompClient.send('/chat.sendMessage.client', {}, JSON.stringify(message));
  //   else
  //     console.log("NU MERGE")
  // }
  //
  // public subscribeToAdmin(callback: (message: any) => void) {
  //   if(this.stompClient){
  //     this.stompClient.subscribe('/topic/ws-chat/admin', (message: any) => {
  //       console.log(message);
  //     });
  //   }
  //   else
  //     console.log("NU MERGE")
  // }
  //
  // public sendMessageToClient(message: any) {
  //   if(this.stompClient)
  //     this.stompClient.send('/chat.sendMessage.admin', {}, JSON.stringify(message));
  //   else
  //     console.log("NU MERGE")
  // }
  //
  // public subscribeToClient(callback: (message: any) => void) {
  //   if(this.stompClient){
  //     this.stompClient.subscribe('/topic/ws-chat/client', (message: any) => {
  //       console.log(message);
  //     });
  //   }
  //   else
  //     console.log("NU MERGE")
  //
  // }

}
