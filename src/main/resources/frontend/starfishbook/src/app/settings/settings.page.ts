import { Component, OnInit } from '@angular/core';
import { ModalController } from '@ionic/angular';


@Component({
  selector: 'app-settings',
  templateUrl: './settings.page.html',
  styleUrls: ['./settings.page.scss'],
})
export class SettingsPage implements OnInit {

  constructor(public modalCtrl: ModalController) {

  }

  darkMode: boolean;
  show: boolean;

  ngOnInit() {


    const prefersDark = window.matchMedia('(prefers-color-scheme: dark)');
    this.darkMode = prefersDark.matches;
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


}
