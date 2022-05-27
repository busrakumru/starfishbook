import { Component, OnInit } from '@angular/core';
import { TokenService } from '../public/services/token/token.service';



@Component({
  selector: 'app-tab2',
  templateUrl: 'tab2.page.html',
  styleUrls: ['tab2.page.scss']
})
export class Tab2Page  implements OnInit {
  currentUser: any;
  constructor(private token: TokenService) { }
  ngOnInit(): void {
    this.currentUser = this.token.getUser();
  }
  
}
