import { Component, OnInit } from '@angular/core';
import { ModalController, PopoverController } from '@ionic/angular';
import { MoodPage } from 'src/app/modals/mood/mood.page';
import { MoodService } from 'src/app/services/mood.service';


@Component({
  selector: 'app-all-moods',
  templateUrl: './all-moods.component.html',
  styleUrls: ['./all-moods.component.scss'],
})
export class AllMoodsComponent implements OnInit {

  constructor(public moodService: MoodService,public modalController: ModalController,private popoverController: PopoverController) { }

  ngOnInit() {
    this.moodService.moodImage;
  }

   
  async openmodal(img) {

    this.popoverController.dismiss();
    const modal = await this.modalController.create({
      component: MoodPage,
      componentProps: {
        'id': img.id,
        'img':img.img,
        'name':img.name,
        'color':img.color
        
      }
      
    });
    return await modal.present();
  }
  
}
