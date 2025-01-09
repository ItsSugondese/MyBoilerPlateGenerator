import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { MessagesModule } from 'primeng/messages';
import { DropdownModule } from 'primeng/dropdown';
import { DataViewModule } from 'primeng/dataview';
import { PanelModule } from 'primeng/panel';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { ChartModule } from 'primeng/chart';
import { InputNumberModule } from 'primeng/inputnumber';
import { TableModule } from 'primeng/table';
import { MultiSelectModule } from 'primeng/multiselect';
import { TagModule } from 'primeng/tag';
import { SliderModule } from 'primeng/slider';
import { ProgressBarModule } from 'primeng/progressbar';
import { PaginatorModule } from 'primeng/paginator';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  exports : [
    DialogModule,
    ButtonModule,
    CalendarModule,
    MessagesModule,
    DropdownModule,
    DataViewModule,
    PanelModule,
    AutoCompleteModule,
    OverlayPanelModule,
    ChartModule,
    InputNumberModule,
    TableModule,
    MultiSelectModule,
    TagModule,
    SliderModule,
    ProgressBarModule,
    PaginatorModule
  ]
})
export class NgprimeModule { }
