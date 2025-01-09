import { OverlayModule } from "@angular/cdk/overlay";
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { NgxModule } from '@shared/module/ngx.module';
import { FormModule } from '../shared/module/form.module';
import { MaterialModule } from '../shared/module/material.module';
import { NgprimeModule } from '../shared/module/ngprime.module';
import { AddButtonComponent } from './buttons/add-buttons.template.component';
import { AnnouncementButtonComponent } from './buttons/announcement-button.template.component';
import { CleanButtonComponent } from './buttons/clean-button.template.component';
import { DefaultButtonComponent } from './buttons/default.buttons.template.component';
import { DisableEnableButtonComponent } from './buttons/disable-buttons.template.component';
import { CalenderTemplateComponent } from './calender/calender.template.componenet';
import { DoughnutSalesComponent } from './chart/doughnut-sales.template.component';
import { TextTypeCustomColorComponent } from './design/type-text/text-type-custom-color.template.component';
import { TextTypeComponent } from './design/type-text/text-type.template.component';
import { PaginationDropdownComponenet } from './dropdown/pagination-dropdown.template.component';
import { FileUploadComponent } from './file-upload/file-upload.template.component';
import { NoContentComponent } from './not-found/no-content.template.component';
import { FoodFilterComponent } from './order/edit-order.component';
import { AnnouncementPopUpComponent } from './pop-up/announcement.template.component';
import { AreYouSureComponent } from './pop-up/are-you-sure.template.component';
import { PayCashComponent } from './pop-up/pay-cash.template.componenet';
import { SearchWithFilterTemplateComponent } from './search/search-with-filter.template.component';
import { SearchTemplateComponent } from './search/search.template.componenet';
import { SnackbarTemplateComponent } from './snackbar/snackbar.template.component';




@NgModule({
  declarations: [
    FoodFilterComponent,
  SnackbarTemplateComponent,
  CalenderTemplateComponent,
  SearchTemplateComponent,
  NoContentComponent,
  TextTypeComponent,
  PaginationDropdownComponenet,
  DisableEnableButtonComponent,
  AddButtonComponent,
  FileUploadComponent,
  DefaultButtonComponent,
  AreYouSureComponent,
  TextTypeCustomColorComponent,
  AnnouncementPopUpComponent,
  AnnouncementButtonComponent,
  DoughnutSalesComponent,
  SearchWithFilterTemplateComponent,
  PayCashComponent,
  CleanButtonComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    NgprimeModule,
    FormModule,
    MaterialModule,
    OverlayModule,
    NgxModule, 
  ],
  exports:[
    FoodFilterComponent,
  SnackbarTemplateComponent,
  CalenderTemplateComponent,
  SearchTemplateComponent,
  NoContentComponent,
  TextTypeComponent,
  PaginationDropdownComponenet,
  DisableEnableButtonComponent,
  AddButtonComponent,
  FileUploadComponent,
  DefaultButtonComponent,
  AreYouSureComponent,
  TextTypeCustomColorComponent,
  AnnouncementPopUpComponent,
  AnnouncementButtonComponent,
  DoughnutSalesComponent,
  SearchWithFilterTemplateComponent,
  PayCashComponent,
  CleanButtonComponent
  ]
})
export class TemplatesModule { }
