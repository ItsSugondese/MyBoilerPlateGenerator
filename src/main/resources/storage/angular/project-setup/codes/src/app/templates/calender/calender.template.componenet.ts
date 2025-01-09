import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

export enum CalenderType{
    DAY, WEEK, MONTH
}

@Component({
  standalone: false,
  selector: 'calender-template',
  template: `
  <div
          class="p-1  px-2 w-fit border-2 border-customPrimary bg-white mt-1  rounded-md shadow-sm flex   focus-within:border-customPrimary text-customPrimary">
          <mat-icon>calendar_today</mat-icon>
          <p-calendar class="outline-none" [(ngModel)]="rangeDates"
            selectionMode="range" [readonlyInput]="true"
          (onSelect)="onRangeSelect($event)">
</p-calendar>
    </div>
  `,
  styles: [`
  
// ::ng-deep .p-datepicker table td > span {
//     background-color: orange;
//   }
::ng-deep .p-datepicker  {
    // background-color: orange;
  }
::ng-deep .p-datepicker-header  {
    background-color: #A62A22;
    color: white
  }
  
  ::ng-deep .p-datepicker table td.p-datepicker-today > span {
    background-color: #B48885;
    color: white;
  }
  ::ng-deep .p-datepicker table td.ng-tns-c3918063748-0 > span {
    // background-color: #A62A22;
    // color: white;
  }

 
  
  ::ng-deep .p-inputtext {
    width: 200px;
    
  }
  ::ng-deep .p-inputtext:hover {
    width: 200px;
    cursor: pointer;
  }
  
  ::ng-deep .p-inputtext:focus {
    outline: none !important;
    border: none !important;
    position: none;
  }
  ::ng-deep .p-highlight {
    outline: none !important;
    border: none !important;
    color: white !important;
    background-color: #CD3125 !important;
    position: none;
  }

::ng-deep .p-calendar .p-inputtext:focus {
    outline: none !important;
    border: none;
}
  `
],
})
export class CalenderTemplateComponent implements OnInit{
    
    @Input() dateFilterType !: CalenderType
    @Output() selectedDate : EventEmitter<Date[]> = new EventEmitter();
    rangeDates : Date[] = []

    constructor() { }
  
    ngOnInit(): void {
       this.setDate()
    }

    setDate(){
        this.rangeDates[0] = new Date()
        this.rangeDates[1] = new Date()
        if (this.dateFilterType == CalenderType.WEEK){
            this.rangeDates[0].setDate(this.rangeDates[0].getDate() - 7);
            this.rangeDates[1] = new Date();
        }else if (this.dateFilterType == CalenderType.MONTH){
            this.rangeDates[0].setDate(this.rangeDates[0].getDate() - 30);
            this.rangeDates[1] = new Date();
        }

        this.emitDate()
    }
    
    onRangeSelect(event: any) {
        if (this.rangeDates.length == 2 && this.rangeDates[1] != null) {
          const fromDate = this.rangeDates[0];
          const toDate = this.rangeDates[this.rangeDates.length - 1];
          this.emitDate();
        }
    }

      emitDate(){
          this.selectedDate.emit(this.rangeDates)
      }
}
