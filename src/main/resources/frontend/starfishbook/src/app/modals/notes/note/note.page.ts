import { HttpClient, HttpResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { ActionSheetController, ModalController } from '@ionic/angular';
import { Observable } from 'rxjs';
import { FileUploadService } from 'src/app/services/file-upload.service';
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

  selectedFiles?: FileList;
  fileName = '';
  imgName = '';
  currentFile?: File;
  message = '';
  fileInfos?: Observable<any>;

  file: File[];

  placeholderTitel = 'Titel';
  placeholderText = 'Text';



  constructor(
    private notesService: NotesService,
    private modalController: ModalController,
    private uploadService: FileUploadService,
    private filesService: FileUploadService
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

  ngOnInit(): void {
    if (this.id) {
      this.fileInfos = this.uploadService.getFiles();
      this.placeholderTitel = this.title;
      this.placeholderText = this.text;
    }
  }

  /*newFile: FormGroup = new FormGroup({
    name: new FormControl(''),
    size: new FormControl('')
  })*/

  newNote: FormGroup = new FormGroup({
    title: new FormControl('',),
    text: new FormControl(''),
    color: new FormControl(''),
    files: new FormArray([
      new FormGroup({
        name: new FormControl('')
      })])

    //files: new FormArray([this.newFile])
  })



  /*add() {
    (this.newNote.controls['files'] as FormArray).push(this.newFile)
  }*/

  get files() {
    return this.newNote.get('files') as FormArray;
  }

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
    this.notesService.updateNote(this.id, this.newNote.value).subscribe(
      (response) => console.log(response),
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
}
