import {Stomp} from "@stomp/stompjs";
import * as SockJS from "sockjs-client";
import {Injectable} from "@angular/core";

@Injectable()
export class WebSocketAPI {
  webSocketEndPoint: string = 'http://localhost:8083/websocket';

  constructor(){
  }

  public connect() {
    // let socket = new SockJS(this.webSocketEndPoint);
    return Stomp.over(() =>{
      return new SockJS(this.webSocketEndPoint);
    });
  }

}
