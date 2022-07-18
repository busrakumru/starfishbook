import { Component, Input, OnInit } from '@angular/core';
import { AlertController } from '@ionic/angular';
import { Mood } from 'src/app/models/mood';
import { MoodService } from 'src/app/services/mood.service';

@Component({
  selector: 'app-profilmood',
  templateUrl: './profilmood.component.html',
  styleUrls: ['./profilmood.component.scss'],
})
export class ProfilmoodComponent implements OnInit {

  moods: Mood[];

  constructor(public alertController: AlertController,private moodService: MoodService) { }

  ngOnInit() {
    this.moodService.getMood().subscribe((data: Mood[]) => {
      this.moods = data;
    })
  }
  
  async deleteMood(mood) {
    const alert = await this.alertController.create({

      header: 'Achtung!',
      message: 'Möchtest du deine Todo  wirklich <strong>löschen</strong>?',
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
            this.moodService.deleteMood(mood.id)
              .subscribe(() => {
                this.moods = this.moods.filter(moods => moods.id != mood.id);
              });
          }
        }
      ]
    });
    await alert.present();
  }
}
