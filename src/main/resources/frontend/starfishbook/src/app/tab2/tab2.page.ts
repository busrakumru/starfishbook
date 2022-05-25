import { Component, OnInit } from '@angular/core';
import { UsersService } from '../services/users.service';
import { AuthService } from '../shared/auth.service';
import { User } from '../shared/user';

@Component({
  selector: 'app-tab2',
  templateUrl: 'tab2.page.html',
  styleUrls: ['tab2.page.scss']
})
export class Tab2Page implements OnInit{

  users: User[];

  constructor(public authService: AuthService,
    public usersService: UsersService) {}
  logout() {
    this.authService.doLogout()
  }

  ngOnInit(): void {

    this.usersService.getUsers().subscribe((data: User[]) => {

      console.log(data);
      this.users = data;
    })}

  
}
