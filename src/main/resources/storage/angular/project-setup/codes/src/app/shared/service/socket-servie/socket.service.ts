import { Injectable } from '@angular/core';
import { Stomp } from '@stomp/stompjs';
import { Subject } from 'rxjs';
import SockJS from 'sockjs-client';
import { environment } from 'src/environments/environment';
import { UserService } from '../user-service/user.service';

@Injectable({
  providedIn: 'root'
})
export class SocketService {
  backendUrl : string = environment.apiUrl
  
  connection: boolean = false;

  stompClient: any

  notificationCountTopic : string = "/user/topic/notification";
  revenueDataTopic : string = "/topic/revenue";
  orderDataTopic : string = "/topic/ping/order";
  tableDataTopic : string = "/topic/table";
  foodMenuDataTopic : string = "/topic/food-menu";
  salesDataTopic : string = "/topic/sales-data";
  usersDataTopic : string = "/topic/users";


  notificationNumberSubject = new Subject<number>();

  // messageSubscription !: Subscription;

  messageNotification = new Subject<any>();
  messageTyping = new Subject<any>();

  webSocketEndPoint!: string
  
  constructor(private userService : UserService){
    
    this.webSocketEndPoint  = `${this.backendUrl}ws?userId=${this.userService.getUserId()}`;
  }

  connect() {
    console.log("initalize  websocket connection");
    let ws = SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(ws);
    const _this = this;
    _this.stompClient.connect({}, function (frames: any) {
  
      if(_this.userService.getSingleRole() == 'ADMIN'){
        // for table data
   
      }

      if(_this.userService.getSingleRole() == 'ADMIN' || _this.userService.getSingleRole() == 'STAFF'){

        // for revenue data
        _this.stompClient.subscribe(_this.revenueDataTopic, function (revenueDataResponse: any) {
          _this.onRevenueDataReceived(revenueDataResponse);
        });

      
      }

      // for notififcation count 
      _this.stompClient.subscribe(_this.notificationCountTopic, function (notificaitonResponse: any) {
        _this.onNotificationCountReceived(notificaitonResponse);
      });

    }, this.errorCallBack);
    if (SockJS.OPEN) {
      this.connection = true;
    }
  };




  disconnect() {
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }
    console.log("Disconnected");
  }

  errorCallBack(error: any) {
    console.log("error ma xu hai ta")
    console.log("errorCallBack -> " + error)
    setTimeout(() => {
      this.connect();
    }, 5000);
  }


  onNotificationCountReceived(message: any) {
    const obj = JSON.parse(message.body) as number
    this.notificationNumberSubject.next(obj);
  }

  onRevenueDataReceived(message: any) {
    // const obj = JSON.parse(message.body) as RevenueData
    // this.revenueSubject.next(obj);
  }
  
}
