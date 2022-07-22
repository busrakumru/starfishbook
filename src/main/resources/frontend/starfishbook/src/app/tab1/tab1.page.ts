import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController, ModalController } from '@ionic/angular';
import { CategoryPage } from '../modals/category/category.page';
import { NotePage } from '../modals/notes/note/note.page';
import { Category } from '../models/category.model';
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

  searchTerm: string;
  notes: Notes[];
  files: File[];
  categories: Category[];

  list: boolean;

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
    private categoriesService: CategoriesService
  ) { }

  ngOnInit(): void {

    this.notesService.getNotes().subscribe((data: Notes[]) => {
      this.notes = data;
    });

    this.filesService.getFiles().subscribe((data: File[]) => {
      this.files = data;
    });

    this.categoriesService.getCategories().subscribe((data: Category[]) => {
      this.categories = data;
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
      breakpoints: [0, 0.3, 0.5, 0.8],
      initialBreakpoint: 0.3
    });
    return await modal.present();
  }
}
