import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';


@Component({
  standalone: false,
  selector: 'clean-button-template',
  template: `
 <button class="bg-{{bg}} text-{{color}} inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50  bg-background hover:bg-accent hover:text-accent-foreground h-10 px-4 py-2"
 (click)=" clicked.emit()" [ngClass]="{'disabled-button': isDisabled}">
      {{text}}
    </button>
  `,
  styles: [
],
})
export class CleanButtonComponent{
    @Output() clicked : EventEmitter<void> = new EventEmitter<void>()
    @Input() text !: string 
    @Input() bg : string = "[#3B82F6]"
    @Input() color : string = "white"
    @Input() isDisabled : boolean = false;

    constructor() { }
  
    
}
