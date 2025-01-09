import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app.routes';
import { BodyComponent } from './body/body.component';
import { UiModule } from './shared/ui/ui.module';
import { TemplatesModule } from './templates/templates.module';
import { AuthInterceptor } from './_auth/auth.interceptor';
import { FeaturesLayoutComponent } from './layouts/features-layout/features-layout.component';
import { LoginLayoutComponent } from './layouts/login-layout/login-layout.component';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    AppComponent,
    BodyComponent,
    FeaturesLayoutComponent,
    LoginLayoutComponent
  ],
  imports: [
    UiModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatIconModule,
    TemplatesModule,
  ],
  
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
