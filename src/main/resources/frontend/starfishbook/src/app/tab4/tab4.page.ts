import { Component, OnInit } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { SettingsPage } from '../settings/settings.page';

@Component({
  selector: 'app-tab4',
  templateUrl: './tab4.page.html',
  styleUrls: ['./tab4.page.scss'],
})
export class Tab4Page implements OnInit {

  constructor(public modalController: ModalController) { }

  ngOnInit() {
  }

  async openModal() {

    const modal = await this.modalController.create({
      component: SettingsPage,
    });
    return await modal.present();
  }

}
