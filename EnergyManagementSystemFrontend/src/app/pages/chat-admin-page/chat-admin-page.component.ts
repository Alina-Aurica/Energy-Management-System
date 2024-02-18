import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {WebSocketChat} from "../../services/websocket/WebSocketChat";
import {UserService} from "../../services/user/user.service";
import {ChatMessage} from "../../models/chat/chatMessage";
import {User} from "../../models/user/User";
import jwt_decode from "jwt-decode";
import {MessageService} from "../../services/chat/message.service";
import {MatSnackBar} from "@angular/material/snack-bar";


@Component({
  selector: 'app-chat-admin-page',
  templateUrl: './chat-admin-page.component.html',
  styleUrls: ['./chat-admin-page.component.css']
})
export class ChatAdminPageComponent implements OnInit{
  chatMessage: ChatMessage = new ChatMessage()
  message: any
  stompClient: any
  stompClientNotification: any
  clientList: String[] = []
  clientChat: any
  clientName: any

  chatList: ChatMessage[] = []
  typingNotificationToClient: string = "Typing..."
  typingNotificationFromClient: string = ""

  constructor(private router: Router,
              private webSocketChat: WebSocketChat,
              private userService: UserService,
              private messageService: MessageService,
              private snackBar: MatSnackBar
  ){
    this.stompClient = this.webSocketChat.connect();
    this.stompClient.connect({}, () => {});

    this.stompClientNotification = this.webSocketChat.connectNotification();
    this.stompClientNotification.connect({}, () => {});
  }

  ngOnInit(): void {
    this.userService.getAllUsersByRole("CLIENT").subscribe(
      (result: User[]) => {
        console.log(result);
        result.forEach((userA) => {
            this.clientList.push(userA.id)
          }
        )
      },
      (_error: Error) => {
        console.log("error");
      }
    )
  }

  startChat(){
    console.log("Suntem in sendToClient!")
    const user: any = localStorage.getItem("token")
    var tokenLoad: any;
    tokenLoad = jwt_decode(user)
    console.log(tokenLoad.clientID)
    this.chatMessage.sender = tokenLoad.clientID;
    this.chatMessage.receiver = this.clientChat;
    this.userService.getUserByID(this.clientChat).subscribe(
      (result) => {
        this.clientName = result.lastName + " " + result.firstName;
      }
    );

    this.stompClient.subscribe("/topic/admin/" + this.clientChat, (message: any) => {
      this.refreshMessage()
    });

    this.stompClientNotification.subscribe("/topic/seen/" + this.clientChat, (message: any) => {
      this.refreshMessage()
    });

    this.stompClient.subscribe("/topic/typing/client/" + this.clientChat, (message: any) => {
      this.typingNotificationFromClient = message;
      this.snackBar.open("Client " + this.clientChat + " typing..., ","", {
            duration: 2000
      });
    })

    this.refreshMessage()
  }

  sendToClient() {
    this.chatMessage.content = this.message;
    this.chatMessage.seen = false;
    const user: any = localStorage.getItem("token")
    var tokenLoad: any;
    tokenLoad = jwt_decode(user)
    console.log(tokenLoad.clientID)
    this.stompClient.send('/topic/admin/' + this.clientChat, {}, JSON.stringify(this.chatMessage));
    // this.refreshMessage();

  }

  sendSeenNotification(message: ChatMessage){
    const user: any = localStorage.getItem("token")
    var tokenLoad: any;
    tokenLoad = jwt_decode(user)
    if(!message.seen && message.receiver === tokenLoad.clientID){
      this.messageService.updateChatMessage(message).subscribe(
        (result) => {
          //this.chatMessage = result
          this.stompClientNotification.send("/topic/seen/" + this.clientChat, {}, JSON.stringify(result));
          this.refreshMessage();
        },
        (_error: Error) => {
          console.log("error");
        })
    }
  }

  sendTypingNotification() {
    const user: any = localStorage.getItem("token")
    var tokenLoad: any;
    tokenLoad = jwt_decode(user)
    this.stompClient.send("/topic/typing/admin/" + this.clientChat, {}, JSON.stringify(this.typingNotificationToClient))
    //this.refreshMessage();
  }

  refreshMessage() {
    this.chatList = []
    this.messageService.getAllConversation(this.chatMessage).subscribe(
      (result) => {
        this.chatList = result
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



}
