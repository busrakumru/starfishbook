import { Component, OnInit } from '@angular/core';
import { AuthService } from '../public/services/auth-service/auth.service';
import { TokenService } from '../public/services/token/token.service';



@Component({
  selector: 'app-tab2',
  templateUrl: 'tab2.page.html',
  styleUrls: ['tab2.page.scss']
})
export class Tab2Page  implements OnInit {
  
  
  constructor(public authService: AuthService) { }
 
  ngOnInit(): void {
   
  }
  logout() {
    this.authService.logout();
  }
}
