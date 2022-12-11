import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController, ModalController } from '@ionic/angular';
import { User } from '../models/user.model';
import { AuthService } from '../public/services/auth-service/auth.service';
import { ReloadService } from '../services/reload.service';
import { UserService } from '../services/user.service';


@Component({
  selector: 'app-settings',
  templateUrl: './settings.page.html',
  styleUrls: ['./settings.page.scss'],
})
export class SettingsPage implements OnInit {

  users: User[] = [];


  constructor(public modalCtrl: ModalController,
    private userService: UserService,
    public alertController: AlertController,
    private authService: AuthService,
    private reloadService: ReloadService,private router: Router ) {

  }

  darkMode: boolean;
  show: boolean;
  toggle = document.querySelector('input[name=theme]');

  ngOnInit() {


    const prefersDark = window.matchMedia('(prefers-color-scheme: dark)');
    this.darkMode = prefersDark.matches;

    this.userService.getUsers().subscribe((data: User[]) => {
      this.users = data;
      console.log(data);
    });

  }

  backtoPage() {
    this.modalCtrl.dismiss();
  }

  change() {
    this.show = !this.show;
  }

  /*onClick(event){
    if(event.detail.checked){
      document.body.setAttribute('color-theme', 'dark')
    }else{
      document.body.setAttribute('color-theme','light')
    }

}*/

  onClick() {
    this.darkMode = !this.darkMode;
    document.body.classList.toggle('dark')
  }

  delete(user) {

    this.authService.delete(user.userid).subscribe(
      () => {
        this.users = this.users.filter(users => users.userid != user.userid);
        console.log('deleted response');
        this.router.navigate(['../../login']);
        this.reloadService.reload();  
      });
    
  }


  /*async delete(){
  
    const alert = await this.alertController.create({
  
      header: 'Achtung!',
      message: 'Möchtest du dein Konto wirklich <strong>löschen</strong>?',
      buttons: [
        {
          text: 'Abbrechen',
          role: 'cancel',
          cssClass: 'secondary',
          id: 'cancel-button',
          handler: () => {
            console.log('Abgebrochen');
          }
        }, {
          text: 'Löschen',
          id: 'confirm-button',
          handler: () => {
            this.userService.deleteUser(user.id)
            .subscribe(() => {
              this.users = this.users.filter(users => users.id != user.id);
            });
          }
        }
      ]
    });
  
    await alert.present();
  
  }*/


  logout() {
    this.authService.logout();
    this.modalCtrl.dismiss();
    this.reloadService.reload();

  }
}
