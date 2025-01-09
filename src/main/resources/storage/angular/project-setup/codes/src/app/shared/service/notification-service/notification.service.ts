import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ServiceCommonVariableComponent } from '@shared/helper/inherit/common-variable-component-serivce';
import { PaginatedData } from 'src/app/constant/data/pagination/pagination.model';
import { ResponseData } from 'src/app/constant/data/response-data.model';
import { environment } from 'src/environments/environment';
import { NotificationPagination } from './model/notification.payload';
import { NotificationModel } from './model/notification.model';

@Injectable({
  providedIn: 'root'
})
export class NotificationService extends ServiceCommonVariableComponent {

  moduleName = "notification"
  backendUrl = environment.apiUrl
  constructor(private httpClient: HttpClient) {
    super()
  }

  getNewNotificationCount(){
    return this.httpClient.get<ResponseData<number>>(`${this.backendUrl}${this.moduleName}/new-notification-count`)
  }

  getUserNotifications(payload: NotificationPagination){
    this.loading = true;
    return this.httpClient.post<ResponseData<PaginatedData<NotificationModel>>>(`${this.backendUrl}${this.moduleName}/paginated`, payload)
    .pipe(
      this.handleError()
    )
  }
}
