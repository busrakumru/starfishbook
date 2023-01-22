import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-admin-board',
  templateUrl: './admin-board.component.html',
  styleUrls: ['./admin-board.component.scss'],
})
export class AdminBoardComponent implements OnInit {

  content = 'HALLOADMIN';

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getAdminBoard().subscribe(
      data => {
        this.content = data;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }
}
