import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertController, ModalController } from '@ionic/angular';
import { NotePage } from '../modals/notes/note/note.page';
import { Notes } from '../models/notes.model';
import { FileUploadService } from '../services/file-upload.service';
import { NotesService } from '../services/notes.service';

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})

export class Tab1Page {

  notes: Notes[];
  files: File[];

  /*categories: [
    {

      title: "Lebensmittel"
    },
    {
      title: "Kleidung"
    }];*/

  categories: string[] = ['Alle', 'Lebensmittel', 'Kleidung', 'Kosmetik'];

  constructor(private notesService: NotesService,
    public router: Router,
    public modalController: ModalController,
    public alertController: AlertController,
    private filesService: FileUploadService
  ) { }

  ngOnInit(): void {

    this.notesService.getNotes().subscribe((data: Notes[]) => {

      console.log(data);
      this.notes = data;
    });

    this.filesService.getFiles().subscribe((data: File[]) => {
      console.log(data);
      this.files = data;
    });
  }

  newNote: FormGroup = new FormGroup({
    title: new FormControl(''),
    text: new FormControl(''),
    color: new FormControl('')
  })


  /*createNewNote() {
    console.log(this.newNote.value);

    this.notesService.createNote(this.newNote.value)
      .subscribe(
        (response) => console.log(response),
        error => {
          console.error(error);

        });
  }*/

  async deleteNote(note) {

    const alert = await this.alertController.create({

      header: 'Achtung!',
      message: 'Möchtest du dein Notiz wirklich <strong>löschen</strong>?',
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
            this.notesService.deleteNote(note.id)
              .subscribe(
                (response) => console.log(response),
                error => {
                  console.error(error);

                });
          }
        }
      ]
    });

    await alert.present();

  }

  async updateNote(note) {

    const modal = await this.modalController.create({
      component: NotePage,
      componentProps: {
        'id': note.id,
        'title': note.title,
        'text': note.text
      }
    });
    return await modal.present();
  }

  async openCard() {

    const modal = await this.modalController.create({
      component: NotePage,
    });
    return await modal.present();
  }
}
