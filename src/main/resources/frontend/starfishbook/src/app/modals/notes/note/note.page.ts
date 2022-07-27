import { HttpClient, HttpResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActionSheetController, ModalController } from '@ionic/angular';

import { Observable } from 'rxjs';
import { Categories } from 'src/app/models/categories.model';
import { Files } from 'src/app/models/file.model';
import { FileUploadService } from 'src/app/services/file-upload.service';
import { NotesService } from 'src/app/services/notes.service';
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

  selectedFiles?: FileList;
  fileName = '';
  imgName = '';
  currentFile?: File;
  message = '';
  fileInfos?: Observable<any>;

  file: Files[];
  //categories: Categories[];

  placeholderTitel = 'Titel';
  placeholderText = 'Text';



  constructor(
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

  //newNote: FormGroup;

  ngOnInit(): void {
    if (this.id) {
      this.fileInfos = this.uploadService.getFiles();
      this.placeholderTitel = this.title;
      this.placeholderText = this.text;
    }


    /*this.newNote = this.fb.group({

      title: [],
      text: [],
      color: [],
      files: this.fb.array([]),
      categories: []

    })
    this.newFile();*/
  }

  get files() {
    return this.newNote.get('files') as FormArray;
  }

  /*newFile(){

    this.files.push( this.fb.group({

    id: [],
    data: [],
    name: [],
    type: []


    })
      
      
      )




  }*/

  /*newFile: FormGroup = new FormGroup({
    name: new FormControl(''),
    size: new FormControl('')
  })*/
  ca = '';

  newNote: FormGroup = new FormGroup({
    title: new FormControl(''),
    text: new FormControl(''),
    color: new FormControl(''),
    files: new FormArray([
      new FormGroup({
        name: new FormControl('')
      })]),
    categories: new FormControl(this.ca)

    //files: new FormArray([this.newFile])
  })

  





  /*add() {
    (this.newNote.controls['files'] as FormArray).push(this.newFile)
  }*/

  

  saveNote(): void {
    if (this.id) {
      this.updateNote();

    } else {
      this.createNewNote();
    }
    this.modalController.dismiss();
  }

  createNewNote() {
    
    this.notesService.createNote(this.newNote.value)
      .subscribe(
        (response) => console.log(response),
        error => {
          console.error(error);
        });
    this.modalController.dismiss();
  }

  updateNote() {
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);
      if (file) {
        this.currentFile = file;
        this.uploadService.upload(this.currentFile).subscribe(
          (event: any) => {
            if (event instanceof HttpResponse) {
              this.message = event.body.message;
              this.fileInfos = this.uploadService.getFiles();
            }
          },
          (err: any) => {
            if (err.error && err.error.message) {
              this.message = err.error.message;
            } else {
              this.message = 'Could not upload the file!';
            }
            this.currentFile = undefined;
          });
      }
      this.selectedFiles = undefined;
    }
    this.notesService.updateNote(this.id, this.newNote.value).subscribe(
      //(response) => console.log(response),
      error => {
        console.error(error);
      });
  }


  abbrechen() {
    this.modalController.dismiss();
  }


  selectImg(event: any): void {
    this.selectedFiles = event.target.files;
    const file: File = event.target.files[0];

    if (file) {
      this.imgName = file.name;
    }
  }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
    const file: File = event.target.files[0];

    if (file) {
      this.fileName = file.name;

      /*const formData = new FormData();

            formData.append("thumbnail", file);

            const upload$ = this.http.post("/auth/users/notes", formData);

            upload$.subscribe();*/
    }
  }

  upload(): void {
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);
      if (file) {
        this.currentFile = file;
        this.uploadService.upload(this.currentFile).subscribe(
          (event: any) => {
            if (event instanceof HttpResponse) {
              this.message = event.body.message;
              this.fileInfos = this.uploadService.getFiles();
            }
          },
          (err: any) => {
            if (err.error && err.error.message) {
              this.message = err.error.message;
            } else {
              this.message = 'Could not upload the file!';
            }
            this.currentFile = undefined;
          });
      }
      this.selectedFiles = undefined;
    }
  }

  deleteFile(file) {
    this.filesService.deleteFile(file.id)
      .subscribe(
        (response) => console.log(response),
        error => {
          console.error(error);

        });
  }

  async showC() {

    const modal = await this.modalController.create({
      component: CategoryListPage,
    });

    modal.onDidDismiss().then(data => {
      const ctgry = data.data.id;
      this.ca = ctgry;
      console.log(this.ca);

      
    })

    return await modal.present();

  }
}
