import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ModalController } from '@ionic/angular';
import { NotesService } from 'src/app/services/notes.service';


@Component({
  selector: 'app-note',
  templateUrl: './note.page.html',
  styleUrls: ['./note.page.scss'],
})
export class NotePage implements OnInit {

  @Input() id: any;
  @Input() title: string;
  @Input() text: string;

  constructor(
    private notesService: NotesService,
    private modalController: ModalController) { }

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
  ngOnInit() { }
 
  newNote: FormGroup = new FormGroup({
    title: new FormControl(''),
    text: new FormControl(''),
    color: new FormControl('')
  })

  saveNote(): void {
    if (this.id) {
      this.updateNote();
    } else {
      this.createNewNote();
    }
    this.modalController.dismiss();
  }

  createNewNote() {
    console.log(this.newNote.value);

    this.notesService.createNote(this.newNote.value)
      .subscribe(
        (response) => console.log(response),
        error => {
          console.error(error);
        });
    this.modalController.dismiss();
  }

  updateNote() {
    this.notesService.updateNote(this.id, this.newNote.value).subscribe(
      (response) => console.log(response),
      error => {
        console.error(error);
      });
  }


  abbrechen(){
    this.modalController.dismiss();
  }
}
