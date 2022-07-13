import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ModalController, PopoverController } from '@ionic/angular';
import { AllMoodsComponent } from '../components/all-moods/all-moods.component';

@Component({
  selector: 'app-tabs',
  templateUrl: 'tabs.page.html',
  styleUrls: ['tabs.page.scss']
})
export class TabsPage {
  
  roleMsg = "";
  constructor(public popoverController: PopoverController) {}
  
  async presentPopover(e: Event) {
    const popover = await this.popoverController.create({
      component: AllMoodsComponent,
      event: e,
      cssClass: 'popover_setting',
    });

    await popover.present();
  
    const { role } = await popover.onDidDismiss();
    this.roleMsg = `Popover dismissed with role: ${role}`;
  }
}
