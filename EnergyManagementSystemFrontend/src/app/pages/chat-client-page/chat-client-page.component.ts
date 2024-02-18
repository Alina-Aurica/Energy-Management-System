import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {WebSocketChat} from "../../services/websocket/WebSocketChat";
import {ChatMessage} from "../../models/chat/chatMessage";
import jwt_decode from "jwt-decode";
import {UserService} from "../../services/user/user.service";
import {User} from "../../models/user/User";
import {MessageService} from "../../services/chat/message.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-chat-client-page',
  templateUrl: './chat-client-page.component.html',
  styleUrls: ['./chat-client-page.component.css']
})
export class ChatClientPageComponent implements OnInit{
  chatMessage: ChatMessage = new ChatMessage()
  message: any
  stompClient: any
  stompClientNotification: any
  adminList: String[] = []
  adminChat: any
  adminName: any

  chatList: ChatMessage[] = []
  typingNotificationToAdmin: string = "Typing..."
  typingNotificationFromAdmin: string = ""



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
    this.userService.getAllUsersByRole("ADMIN").subscribe(
      (result: User[]) => {
        console.log(result);
        result.forEach((userA) => {
          this.adminList.push(userA.id)
          }
        )
      },
      (_error: Error) => {
        console.log("error");
      }
    );
  }

  startChat(){
    this.chatList = []
    console.log("Suntem in sendToAdmin!")
    const user: any = localStorage.getItem("token")
    var tokenLoad: any;
    tokenLoad = jwt_decode(user)
    console.log(tokenLoad.clientID)
    this.chatMessage.sender = tokenLoad.clientID;
    this.chatMessage.receiver = this.adminChat;
    this.userService.getUserByID(this.adminChat).subscribe(
      (result) => {
        this.adminName = result.lastName + " " + result.firstName;
      }
    );

    this.stompClient.subscribe("/topic/admin/" + tokenLoad.clientID, (message: any) => {
      this.refreshMessage()
    });

    this.stompClientNotification.subscribe("/topic/seen/" + tokenLoad.clientID, (message: any) => {
      this.refreshMessage()
    });

    this.stompClient.subscribe("/topic/typing/admin/" + tokenLoad.clientID, (message: any) => {
      this.typingNotificationFromAdmin = message;
      this.snackBar.open("Admin typing...", "", {
        duration: 2000
      });
    })

    this.refreshMessage()
  }

  sendToAdmin() {
    this.chatMessage.content = this.message;
    this.chatMessage.seen = false;
    const user: any = localStorage.getItem("token")
    var tokenLoad: any;
    tokenLoad = jwt_decode(user)
    this.stompClient.send('/topic/admin/' + tokenLoad.clientID, {}, JSON.stringify(this.chatMessage));
    // this.refreshMessage();
  }

  sendSeenNotification(message: ChatMessage){
    const user: any = localStorage.getItem("token")
    var tokenLoad: any;
    tokenLoad = jwt_decode(user)
    if(!message.seen && message.receiver === tokenLoad.clientID){
      // console.log("Mesaj" + message)
      this.messageService.updateChatMessage(message).subscribe(
        (result) => {
          //this.chatMessage = result
          this.stompClientNotification.send("/topic/seen/" + tokenLoad.clientID, {}, JSON.stringify(result));
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
    this.stompClient.send("/topic/typing/client/" + tokenLoad.clientID, {}, JSON.stringify(this.typingNotificationToAdmin))
    //this.refreshMessage();
  }

  refreshMessage() {
    this.chatList = []
    this.messageService.getAllConversation(this.chatMessage).subscribe(
      (result) => {
        this.chatList = result
        console.log(result)
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
