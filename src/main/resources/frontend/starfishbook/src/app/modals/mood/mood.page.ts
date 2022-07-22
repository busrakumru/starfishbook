import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ModalController, PopoverController } from '@ionic/angular';
import { Mood } from 'src/app/models/mood';
import { MoodService } from 'src/app/services/mood.service';

@Component({
  selector: 'app-mood',
  templateUrl: './mood.page.html',
  styleUrls: ['./mood.page.scss'],
})

export class MoodPage implements OnInit {

  moods: Mood[];
  newMood: FormGroup;
  @Input() id: any;
  @Input() name: String;
  @Input() img: string;
  @Input() color: string;

  constructor(private moodService: MoodService, private modalController: ModalController, private route: Router) { }

  ngOnInit() {
    this.newMood = new FormGroup({
      name: new FormControl(this.name),
      img: new FormControl(this.img),
      day: new FormControl(''),
      daily: new FormControl(''),
      color: new FormControl(this.color)
    })

  }

  abbrechen() {
    this.modalController.dismiss();
  }

  speichern(): void {
    this.moodService.createMood(this.newMood.value)
      .subscribe(
        (response) => console.log(response),
        error => {
          console.error(error);
        });
    this.modalController.dismiss();
    this.route.navigate(['/tabs/tab4']);

  }

}
