import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController, ModalController } from '@ionic/angular';
import { CategoryPage } from '../modals/category/category.page';
import { NotePage } from '../modals/notes/note/note.page';
import { Categories } from '../models/categories.model';
import { Files } from '../models/file.model';
import { Notes } from '../models/notes.model';
import { CategoriesService } from '../services/categories.service';
import { FileUploadService } from '../services/file-upload.service';
import { NotesService } from '../services/notes.service';

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})

export class Tab1Page {

  searchTerm: any;
  notes: Notes[];
  categories: Categories[];

  list: boolean;

  z: boolean;

  fileName = '';

  colorPalette: Array<any> = [
    '#D99274',
    '#FAC9C1',
    '#E9D8B8',
    '#DACEE7',
    '#C0C49C',
    '#98C3B0',
    '#FEC888',
    '#C6BBBA',
  ]

  displayMode: number;
  constructor(private notesService: NotesService,
    public router: Router,
    public modalController: ModalController,
    public alertController: AlertController,
    private filesService: FileUploadService,
    private categoriesService: CategoriesService,
    private http: HttpClient
  ) { }

  ngOnInit(): void {

    this.notesService.getNotes().subscribe((data: Notes[]) => {
      this.notes = data;
      console.log(data);
    });

    this.categoriesService.getCategories().subscribe((data: Categories[]) => {
      this.categories = data;
      console.log(data);

    });
  }

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
            this.notesService.deleteNotes(note.id)
              .subscribe(() => {
                this.notes = this.notes.filter(notes => notes.id != note.id);
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
        'text': note.text,
        'color': note.color,
        'categories': note.categories,
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

  changeView() {
    this.list = !this.list;
  }

  passValue(c) {
    this.searchTerm = c;
  }

  clearSearch() {
    this.searchTerm = '';
  }
  
  async addCategory() {
    const modal = await this.modalController.create({
      component: CategoryPage,
    });
    return await modal.present();
  }

}
