import { Component, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../auth-service/auth.service';
import { ForgotPassword, ValidateToken } from '../../auth-service/model/auth.model';
import { Subscription, from } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  standalone: false,
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent implements OnDestroy {

  forgotPasswordSubscription$ !: Subscription
  form: FormGroup;
  
  constructor(private fb: FormBuilder, private authService: AuthService,
    public router: Router){
      this.form = this.fb.group({
        email: ['', [Validators.required, Validators.email]],
      });

    }
  
  onSubmit() {
    //this.formSubmitAttempt = false;
    if (this.form.valid) {
      const val : ForgotPassword = {
        userEmail:  this.formValue('email')?.value
      }
    this.forgotPasswordSubscription$ =   this.authService.forgotPassword(val).subscribe(
      (res) => {
        this.forgotPasswordSubscription$.unsubscribe()
      }
     )
    } 
  }

  formValue(name: string) {
    return this.form.get(name);
  }

  ngOnDestroy(): void {
    if(this.forgotPasswordSubscription$){
      this.forgotPasswordSubscription$.unsubscribe()
    }
  }
}
