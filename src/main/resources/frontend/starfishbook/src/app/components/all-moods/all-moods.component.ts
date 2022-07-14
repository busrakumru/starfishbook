import { Component, OnInit } from '@angular/core';
import { ModalController, PopoverController } from '@ionic/angular';
import { MoodPage } from 'src/app/modals/mood/mood.page';

@Component({
  selector: 'app-all-moods',
  templateUrl: './all-moods.component.html',
  styleUrls: ['./all-moods.component.scss'],
})
export class AllMoodsComponent implements OnInit {

  constructor(public modalController: ModalController,private popoverController: PopoverController) { }

  ngOnInit() {}

   
  async openmodal() {
    this.popoverController.dismiss();
    const modal = await this.modalController.create({
      component: MoodPage,
      
    });
    return await modal.present();
  }
  
}
