import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Notes } from '../models/notes.model';
import { NotesService } from '../services/notes.service';

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})

export class Tab1Page implements OnInit {

  
  notes: Notes = {
    title: '',
    text: ''
  };

  constructor( private notesService: NotesService,
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
  }
}
