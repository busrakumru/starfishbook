import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ModalController, PopoverController } from '@ionic/angular';

@Component({
  selector: 'app-mood',
  templateUrl: './mood.page.html',
  styleUrls: ['./mood.page.scss'],
})
export class MoodPage implements OnInit {

  constructor( private modalController: ModalController, private route: Router) { }

  ngOnInit() {
  }

  abbrechen(){
    this.modalController.dismiss();
  }

  speichern(){
    this.modalController.dismiss();
    this.route.navigate(['/tabs/tab4'], { queryParams: {  } });
  }
}
