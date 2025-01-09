import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormModule } from '../shared/module/form.module';
import { MaterialModule } from '../shared/module/material.module';
import { NgprimeModule } from '../shared/module/ngprime.module';
import { NgxModule } from '../shared/module/ngx.module';
import { UiModule } from '../shared/ui/ui.module';
import { TemplatesModule } from '../templates/templates.module';
import { FeaturesRoutingModule } from './features-routing.module';



@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    FeaturesRoutingModule,
    UiModule,
    NgprimeModule,
    FormModule,
    NgxModule,
    FlexLayoutModule,
    MaterialModule,
    TemplatesModule
  ]
})
export class FeaturesModule { }
