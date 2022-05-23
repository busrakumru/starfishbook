import { Injectable } from "@angular/core";
import { ModalController } from "@ionic/angular";
import { NotePage } from "../modals/notes/note/note.page";

@Injectable({
    providedIn: 'root'
  })
  
  export class ModalControllerService {

    constructor(public modalController: ModalController){}

    async openModal(){

        const modal = await this.modalController.create({
            component: NotePage,
          });
          return await modal.present();

    }
   
  }