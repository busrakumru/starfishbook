import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertController, ModalController } from '@ionic/angular';
import { NotePage } from '../modals/notes/note/note.page';
import { Notes } from '../models/notes.model';
import { NotesService } from '../services/notes.service';

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})

export class Tab1Page  {

  notes: Notes[];
  showCard = true;

  n: Notes = {
    title: '',
    text: '',
    color: ''
  };

  /*selected?: Notes;
  currentIndex: number = -1;
  title: string = '';
  text: string = '';*/

  constructor(private notesService: NotesService,
    private route: ActivatedRoute,
    public router: Router,
    public modalController: ModalController,
    public alertController: AlertController
  ) {

    //this.newNote.valueChanges.subscribe(console.log)
  }

  ngOnInit(): void {

    this.notesService.getNotes().subscribe((data: Notes[]) => {

      console.log(data);
      this.notes = data;
    })


    /*this.route.queryParams
      .subscribe(params => {
          if (params.title) {
            this.getNotesByTitle(params.title);
          } else {
            this.getNotes();
          }
        }
      );
      
      this.notesService.getAll();
      console.log(this.notesService.getAll());*/
  }

  newNote: FormGroup = new FormGroup({
    title: new FormControl(''),
    text: new FormControl(''),
    color: new FormControl('')
  })


  createNewNote() {
    console.log(this.newNote.value);

    this.notesService.createNote(this.newNote.value)
      .subscribe(
        (response) => console.log(response),
        error => {
          console.error(error);

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




    /* this.notesService.deleteNote(note.id)
      .subscribe(
        (response) => console.log(response),
        error => {
          console.error(error);
        
        });*/
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

    /*this.notesService.getNote(note.id).subscribe(
      (response) =>
        this.notesService.updateNote(response, note)
    )*/
  }

  async openCard() {

    const modal = await this.modalController.create({
      component: NotePage,
    });
    return await modal.present();


    //this.modalController.openModal();
  }

  click() {
    console.log("hallo");
  }


  /*getNotesByTitle(title: string): void {
    this.notesService.findByTitle(title)
      .subscribe(
        data => {
          this.notes = data;
        },
        error => {
          console.error(error);
        });
  }

  getNotes(): void {
    this.notesService.getAll()
      .subscribe(
        data => {
          this.notes = data;
        },
        error => {
          console.error(error);
        });
  }

  refreshList(): void {
    this.getNotes();
    this.selected = undefined;
    this.currentIndex = -1;
  }

  setSelected(notes: Notes, index: number): void {
    if (this.selected && this.selected.id == notes.id) {
      this.selected = undefined;
      this.currentIndex = -1;
    } else {
      this.selected = notes;
      this.currentIndex = index;
    }
  }

  searchTitle(): void {
    this.selected = undefined;
    this.currentIndex = -1;

    this.notesService.findByTitle(this.title)
      .subscribe(
        data => {
          this.notes = data;
        },
        error => {
          console.error(error);
        });
  }

  deleteNote(): void {
    if (!this.selected) {
      return;
    }

    this.notesService.delete(this.selected.id)
      .subscribe(
        response => {
          this.refreshList();
        },
        error => {
          console.error(error);
        });
  }

  async presentModal() {
    const modal = await this.modalController.create({
      component: NotePage,
      cssClass: 'note.page.scss'
    });
    return await modal.present();
  }

  navi(){
    this.router.navigate(['note']);

  }*/






  /*constructor( private notesService: NotesService,
    private route: ActivatedRoute,
    private router: Router) {

    }
 
    ngOnInit(): void {
      const id = this.route.snapshot.params.id;
      if (id) {
        this.editPost(this.route.snapshot.params.id);
      }
  }
  
  editPost(id: string): void {
    this.notesService.get(id)
      .subscribe(
        data => {
          this.notes = data;
        },
        error => {
          console.error(error);
        });
  }
 
  savePost(): void {
   

    if (this.notes.id) {
      this.saveEditedPost();
    } else {
      this.createNewPost();
    }
  }
  private createNewPost() {
    this.notesService.create(this.notes)
      .subscribe(
        response => {
          this.router.navigate([ '/notes' ]);
        },
        error => {
          console.error(error);
        
        });
  }
  
  private saveEditedPost() {
    this.notesService.update(this.notes.id, this.notes)
      .subscribe(
        response => {
          this.router.navigate([ '/notes' ]);
        },
        error => {
          console.error(error);
        
        });
  }*/
}
