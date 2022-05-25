import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { AuthService } from '../../shared/auth.service';
import { Router } from '@angular/router';
import { UsersService } from 'src/app/services/users.service';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
})
export class SignupComponent implements OnInit {
  signupForm: FormGroup;
  constructor(
    public fb: FormBuilder,
    public authService: AuthService,
    public router: Router,
    public userService: UsersService
  ) {
   /* this.signupForm = this.fb.group({
      email: [''],
      mobile: [''],
      password: [''],
    });
  }*/

  this.signupForm = this.fb.group({
    email: new FormControl(''),
    password: new FormControl('')
  });
}
  ngOnInit() {}
  registerUser() {
    this.authService.signUp(this.signupForm.value).subscribe(
      (response) => console.log(response),
      error => {
        console.error(error);

      });
  }
}
