import { Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
// import { CommonVariableComponent } from '@shared/helper/inherit/common-variable';
import { CommonVariableComponent } from '@shared/helper/inherit/common-variable-component';
import { EnumItem } from '@shared/model/enums/MapForEnum.model';
import { UserService } from '@shared/service/user-service/user.service';
import { Observable, Subscription, delay, of } from 'rxjs';
import { AuthService } from 'src/app/_auth/auth-service/auth.service';
import { ResponseData } from 'src/app/constant/data/response-data.model';
import { UserNavConstant } from 'src/app/constant/navbar/usernav-data.model';
import { ManagementRouteConstant } from 'src/app/constant/routing/management-routing-constant.model';
import { UserProfileService } from 'src/app/shared/service/user-profile-service/user-profile.service';
import { SidenavService } from '../sidenav/sidenav-service/sidenav.service';
import { NotificationService } from '@shared/service/notification-service/notification.service';
import { NotificationPagination } from '@shared/service/notification-service/model/notification.payload';
import { NotificationModel } from '@shared/service/notification-service/model/notification.model';
import { PaginatedData } from 'src/app/constant/data/pagination/pagination.model';
import { SocketService } from '@shared/service/socket-servie/socket.service';

enum HeaderNav {
  HOMEPAGE = "Homepage",
  ORDER = "My Orders",
}



@Component({
  selector: 'app-header',
  standalone: false,
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent extends CommonVariableComponent implements OnInit, OnDestroy {

  orderUrl: string = `/${ManagementRouteConstant.orderManagement}`
  allowedUrl: string[] = [this.orderUrl]
  navList = UserNavConstant
  inspecting = HeaderNav
  // options: EnumItem[] = this.enumToEnumItems(HeaderNav);
  selectedNavbar = HeaderNav.HOMEPAGE
  userSubscription$ !: Subscription
  userPictureSubscription$ !: Subscription
  // userSubscription$ !: Observable<ResponseData<User>>
  notificationCountSubscription$ !: Subscription
  notificationCountSocketSubscription$ !: Subscription
  // notificationCountSubscription$ !: Observable<ResponseData<number>>

  notificationPaginatedData !: ResponseData<PaginatedData<NotificationModel>>
  notificationPayload !: NotificationPagination

  notificationsSubscription$ !: Subscription
  isOpen = false;

  notificationCount !: number
  notifications: NotificationModel[] = []
  imageMap : string | null = null








  backDropClick() {
    this.notificationPayload.page = 1;
    this.isOpen = false;
    this.notifications = []
  }


  constructor(private userProfileService: UserProfileService, public userService: UserService,
    public router: Router, public sidenavService: SidenavService,
  private socketService: SocketService) {
    super()
  }


  ngOnInit(): void {
    this.socketService.connect()
    this.notificationCountSocketSubscription$ =this.socketService.notificationNumberSubject.subscribe(
      (res) => {
        this.notificationCount = res
      }
    )
    this.userSubscription$ = this.userProfileService.getUserProfile().subscribe(
      (res) => {




      }
    )


  }

  updateSelectedNavbar(value: string) {
    this.selectedNavbar = value as HeaderNav;
  }



  getNotifications() {
    // this.notificationsSubscription$ = this.notificationService.getUserNotifications(this.notificationPayload).subscribe(
    //   (res) => {

    //   }
    // )
  }


  ngOnDestroy(): void {
    if (this.notificationsSubscription$) {
      this.notificationsSubscription$.unsubscribe()
    }
    if(this.notificationCountSocketSubscription$){
      this.notificationCountSocketSubscription$.unsubscribe()
    }
    this.socketService.disconnect()
  }
}
