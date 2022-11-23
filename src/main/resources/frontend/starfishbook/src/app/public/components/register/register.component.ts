import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
//import { ToastrService } from 'ngx-toastr';
import { tap } from 'rxjs/operators';
import { AuthService } from '../../services/auth-service/auth.service';
import { CustomValidators } from '../../_helpers/custom-validators';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  form: FormGroup = new FormGroup({
    email: new FormControl(null, [Validators.required, Validators.email]),
    password: new FormControl(null, [Validators.required]),
    passwordConfirm: new FormControl(null, [Validators.required])
  },
    { validators: CustomValidators.passwordsMatching }
  );

  constructor(private authService: AuthService,
    private router: Router,
    //private toastr: ToastrService
    ) { }

  register() {
    if (this.form.valid) {
      this.authService.register({
        email: this.email.value,
        password: this.password.value
      }).pipe(
        tap(() => this.router.navigate(['../login']))
      ).subscribe();
        //this.toastr.success("EMAIL");
    }
  }

  get email(): FormControl {
    return this.form.get('email') as FormControl;
  }

  get password(): FormControl {
    return this.form.get('password') as FormControl;
  }

  get passwordConfirm(): FormControl {
    return this.form.get('passwordConfirm') as FormControl;
  }

}