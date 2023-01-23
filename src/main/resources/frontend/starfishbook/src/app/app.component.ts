import { Component, OnInit } from '@angular/core';
import { TokenService } from './public/services/token/token.service';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent implements OnInit{
  private roles: string[];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  email: string;

  constructor(private tokenStorageService: TokenService) { }

  ngOnInit(): void {
  }


}
