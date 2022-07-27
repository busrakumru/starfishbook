import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ModalController } from '@ionic/angular';
import { Categories } from 'src/app/models/categories.model';
import { CategoriesService } from 'src/app/services/categories.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.page.html',
  styleUrls: ['./category.page.scss'],
})
export class CategoryPage implements OnInit {

  /*@Input() id: any;
  @Input() title: string;*/
  categories: Categories[];

  constructor(
    private categoriesService: CategoriesService,
    private modalController: ModalController
  ) { }

  ngOnInit() {
    this.categoriesService.getCategories().subscribe((data: Categories[]) => {
      this.categories = data;
    });

    /*if (this.id){
      console.log(this.id, this.title);
    }*/
  }


  newCategory: FormGroup = new FormGroup({
    title: new FormControl('',)
  })

  saveCategory() {
    this.categoriesService.createCategory(this.newCategory.value)
      .subscribe(
        (response) => console.log(response),
        error => {
          console.error(error);
        });
    this.modalController.dismiss();
  }

  cancel() {
    this.modalController.dismiss();
  }

  delete(id: any): void {
    this.categoriesService.delete(id)
      .subscribe(() => {
        this.categories = this.categories.filter(categories => categories.id != id);
      });
  }
}
