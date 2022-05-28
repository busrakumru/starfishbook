import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';

import { AuthService } from '../../services/auth-service/auth.service';
import { TokenService } from '../../services/token/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';

  form: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required])
  });

  constructor(private authService: AuthService, private router: Router, private tokenservice: TokenService) {}

  ngOnInit(): void {
    if (this.tokenservice.getToken()) {
      this.isLoggedIn = true;
    }
  }

  login() {

    if (this.form.valid) {
      this.authService.login({
        email: this.email.value,
        password: this.password.value
      }).pipe(
        tap(data => {

          this.tokenservice.saveToken(data.accessToken);
          this.isLoginFailed = false;
          this.isLoggedIn = true;
          //console.log(data.accessToken);
          this.router.navigate(['../../tabs/tab1'])

        },
          err => {
            this.errorMessage = err.error.message;
            this.isLoginFailed = true;
          })

      ).subscribe()
    }
  }

  get email(): FormControl {
    return this.form.get('email') as FormControl;
  }

  get password(): FormControl {
    return this.form.get('password') as FormControl;
  }

}