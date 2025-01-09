import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../auth-service/auth.service';
import { CommonVariableComponent } from '@shared/helper/inherit/common-variable-component';
import { FormBuilder, Validators } from '@angular/forms';
import { ChangePassword, ForgotPassword, ValidateToken } from '../../auth-service/model/auth.model';
import { Subscription } from 'rxjs';
import { ManagementRouteConstant } from 'src/app/constant/routing/management-routing-constant.model';

@Component({
  standalone: false,
  selector: 'app-password-reset',
  templateUrl: './password-reset.component.html',
  styleUrls: ['./password-reset.component.scss']
})
export class PasswordResetComponent extends CommonVariableComponent implements OnInit, OnDestroy{

  token !: string
  form : any
  reEntered !: string
  changePasswordSubscription$ !: Subscription
  verifyTokenSubscription$ !: Subscription

  constructor(private route: ActivatedRoute, public authService: AuthService, private fb: FormBuilder,
    public router: Router){
    super()

    this.form = this.fb.group({
    password: ['', [Validators.required]],
    reEntered: ['', [Validators.required]],
  });
  }



  ngOnInit(): void {
    this.route.params.subscribe(
      (params) => {
        this.token = params['token']
        const val : ValidateToken = {
          resetToken: this.token
        }
        this.verifyTokenSubscription$ = this.authService.validatePasswordToken(val).subscribe(
          (res) => {this.verifyTokenSubscription$.unsubscribe()},
          (err) => this.router.navigate(['/' + ManagementRouteConstant.forgotPassword])
        )
      }
    );
  }

  onSubmit() {
    //this.formSubmitAttempt = false;
    if (this.form.valid) {
      const val : ChangePassword = {
        resetToken: this.token,
        password: this.formValue('password')?.value
      }
    this.changePasswordSubscription$ =   this.authService.resetPassword(val).subscribe(
      (res) => {
        this.router.navigate(['/' + ManagementRouteConstant.login])
      }
     )
    }
  }

  formValue(name: string) {
    return this.form.get(name);
  }

  ngOnDestroy(): void {
    if(this.changePasswordSubscription$){
      this.changePasswordSubscription$.unsubscribe()
    }
    if(this.verifyTokenSubscription$){
      this.verifyTokenSubscription$.unsubscribe()
    }
  }

}
