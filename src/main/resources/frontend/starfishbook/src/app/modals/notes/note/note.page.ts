import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import {  ModalController } from '@ionic/angular';
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

  constructor(
    public reloadService: ReloadService,
    private notesService: NotesService,
    private modalController: ModalController,
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
    });
  }
 
  newCategorie: FormGroup= new FormGroup({
    id: new FormControl(this.ca),
    //title: new FormControl(''),
  });

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
      //this.newNote.controls.categories.get('id').patchValue(this.ca);
      this.newCategorie.controls.id.patchValue(this.ca);
      this.newNote.addControl('categories', this.newCategorie);
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
