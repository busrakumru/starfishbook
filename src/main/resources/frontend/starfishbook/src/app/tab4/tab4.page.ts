import { Component, OnInit } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { TokenService } from '../public/services/token/token.service';
import { SettingsPage } from '../settings/settings.page';

@Component({
  selector: 'app-tab4',
  templateUrl: './tab4.page.html',
  styleUrls: ['./tab4.page.scss'],
})
export class Tab4Page implements OnInit {

  constructor(public modalController: ModalController, public token: TokenService) { }

  currentUser: any;
  ngOnInit() {
    this.currentUser = this.token.getUser();
  }

  async openModal() {

    const modal = await this.modalController.create({
      component: SettingsPage,
    });
    return await modal.present();
  }

}
