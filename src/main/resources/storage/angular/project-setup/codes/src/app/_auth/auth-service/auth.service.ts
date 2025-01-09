import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ServiceCommonVariableComponent } from '@shared/helper/inherit/common-variable-component-serivce';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Observable } from 'rxjs/internal/Observable';
import { ResponseData } from 'src/app/constant/data/response-data.model';
import { SnackbarService } from 'src/app/templates/snackbar/snackbar-service/snackbar.service';
import { environment } from 'src/environments/environment';
import { ChangePassword, ForgotPassword, LoginModel, ValidateToken } from './model/auth.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService extends ServiceCommonVariableComponent{

  private loginStatus = new BehaviorSubject<boolean>(this.loggedIn())
  private username = new BehaviorSubject<string>(localStorage.getItem('username')!)
  private path = environment.apiUrl
  moduleName: string = "auth"

  isGoogleLogin : boolean = false;


  constructor(private httpClient: HttpClient, private snackService: SnackbarService) {
    super()
   }

  validatePasswordToken(data: ValidateToken){
    this.loading = true
    return this.httpClient.post<ResponseData<string>>(this.path + this.moduleName + "/validate-token", data)
    .pipe(
      this.handleError()
    )
  }

  forgotPassword(data: ForgotPassword){
    this.loading = true;
    return this.httpClient.post<ResponseData<string>>(this.path + this.moduleName + "/forgot-password", data)
    .pipe(
      this.handleError()
    )
  }

  resetPassword(data: ChangePassword){
    this.loading = true;
    return this.httpClient.post<ResponseData<string>>(this.path + this.moduleName + "/reset-password", data)
    .pipe(
      this.handleError()
    )
  }
  loginUser(data: LoginModel){
    this.loading = true;
    return this.httpClient.post<ResponseData<string>>(this.path + this.moduleName + "/login", data)
    .pipe(
      this.handleError()
    )
  }

  // validatePasswordToken(data: ValidateToken){
  //   this.loading = true
  //   return this.httpClient.post<ResponseData<string>>(this.path + this.moduleName + "/validate-token", data)
  //   .pipe(
  //     this.handleError()
  //   )
  // }



  public signOutExternal = () => {
      localStorage.removeItem("token");
      console.log("token deleted")
  }

  LoginWithGoogle(credentials: string): Observable<any> {
    this.isGoogleLogin = true
    return this.httpClient.post(this.path + "auth/login-with-google", JSON.stringify(credentials),)
    // const header = new HttpHeaders().set('Content-type', 'application/json');
    // return this.httpClient.post(this.path + "auth/login-with-google", JSON.stringify(credentials), { headers: header, withCredentials: true })
    // .pipe(
    //   catchError(error => {
    //     this.isGoogleLogin = false;
    //     console.log("here is error" + error)
    //     this.snackService.showMessage({
    //       // label : error.error.message,
    //       label: error.error.message,
    //       status: MessageStatus.FAIL
    //   });
    //     throw error;
    //   }),
    //   finalize(() => this.isGoogleLogin = false)
    // );
  }








  saveToken(token:string) {
    localStorage.setItem('token', token)
  }

  saveUsername(username:string) {
    localStorage.setItem('username', username)
  }

  loggedIn(): boolean {
    if (localStorage.getItem('token')) {
      return true;
    }
    return false;
  }

  setLoginStatus(val:any) {
    this.loginStatus.next(val)
  }

  setUsername(val:any) {
    this.username.next(val)
  }


}
