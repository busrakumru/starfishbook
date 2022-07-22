import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ModalController } from '@ionic/angular';
import { Category } from 'src/app/models/category.model';
import { CategoriesService } from 'src/app/services/categories.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.page.html',
  styleUrls: ['./category.page.scss'],
})
export class CategoryPage implements OnInit {

  @Input() title: string;
  categories: Category[];

  constructor(
    private categoriesService: CategoriesService,
    private modalController: ModalController
  ) { }

  ngOnInit() {
    this.categoriesService.getCategories().subscribe((data: Category[]) => {
      this.categories = data;
    });
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
}
