import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { tap } from 'rxjs/operators';
import { NotificationService } from 'src/app/services/notification.service';
import { AuthService } from '../../services/auth-service/auth.service';
import { CustomValidators } from '../../_helpers/custom-validators';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  isLoginFailed = false;
  errorMessage = '';

  form: FormGroup = new FormGroup({
    email: new FormControl(null, [Validators.required, Validators.email]),
    password: new FormControl(null, [Validators.required]),
    passwordConfirm: new FormControl(null, [Validators.required])
  },
    { validators: CustomValidators.passwordsMatching }
  );


  constructor(private authService: AuthService,
    private router: Router,
    private nofification: NotificationService
    ) { }
 

  register() {
    if (this.form.valid) {
      
      this.authService.register({
        email: this.email.value,
        password: this.password.value
      }).pipe(
        tap(() => {this.router.navigate(['../login']);
        this.nofification.showSuccess("Super! Dir wurde ein Link zur Verifizierung an deiner E-Mail Adresse zugeschickt. Bitte bestätige es.");
      },
        err => {
          this.nofification.showError("Dieser Nutzerkonto existiert bereits!");
          this.errorMessage = err.error.message;
          this.isLoginFailed = true;
        })).subscribe();
      
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