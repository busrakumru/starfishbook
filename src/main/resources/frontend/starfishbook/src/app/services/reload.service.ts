import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ReloadService {

  constructor() { }
  

  reload(){
    setTimeout(() => {
      window.location.reload();
    }, 1000);
  }
}
