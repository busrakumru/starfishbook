import { HttpClient, HttpResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActionSheetController, ModalController } from '@ionic/angular';

import { Observable } from 'rxjs';
import { Categories } from 'src/app/models/categories.model';
import { Files } from 'src/app/models/file.model';
import { FileUploadService } from 'src/app/services/file-upload.service';
import { NotesService } from 'src/app/services/notes.service';
import { ReloadService } from 'src/app/services/reload.service';
import { CategoryListPage } from '../../category-list/category-list.page';



@Component({
  selector: 'app-note',
  templateUrl: './note.page.html',
  styleUrls: ['./note.page.scss'],
})
export class NotePage implements OnInit {


  @Input() id: any;
  @Input() title: string;
  @Input() text: string;
  @Input() color: string;

  // categories: Categories[];

  placeholderTitel = 'Titel';
  placeholderText = 'Text';

  constructor(
    public reloadService: ReloadService,
    private notesService: NotesService,
    private modalController: ModalController,
    private uploadService: FileUploadService,
    private filesService: FileUploadService,
    private fb: FormBuilder
  ) { }

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
  ca = '';

  newNote: FormGroup;
  ngOnInit(): void {


    this.newNote = this.fb.group({
      title: new FormControl(''),
      text: new FormControl(''),
      color: new FormControl(''),
      
      categories:  new FormGroup({
        id: new FormControl(),
    
      })

    })
    
  }

  /* get categories() {
     return this.newNote.get('categories') as FormArray;
   }

  newCategorie: FormGroup = new FormGroup({
    id: new FormControl(this.ca),

  })

   add() {
    // (this.newNote.controls['categories'] as FormArray).push(this.newCategorie)
    return this.newNote.controls.categories.get('id').patchValue(this.ca);
    console.log(this.ca)
   }*/

  updateNote() {
    this.notesService.updateNotes(this.id, this.newNote.value).subscribe(
      (response) => console.log(response),
      error => {
        console.error(error);
      });
  }


  abbrechen() {
    this.modalController.dismiss();
  }


  async showC() {

    const modal = await this.modalController.create({
      component: CategoryListPage,
    });

    modal.onDidDismiss().then(data => {
      const ctgry = data.data.id;
      this.ca = ctgry;
      console.log(this.ca);
      //this.newNote.controls.categories.get('id').patchValue(ca);
    })
    return await modal.present();

  }

  saveNote(): void {
    if (this.id) {
      this.updateNote();

    } else {
      this.createNewNote();
    }
    //this.reloadService.reload();
    this.modalController.dismiss();
  }

  createNewNote() {

    this.notesService.createNotes(this.newNote.value)
      .subscribe(
        (response) => console.log(response),
        error => {
          console.error(error);
        });
    this.modalController.dismiss();
  }

}
