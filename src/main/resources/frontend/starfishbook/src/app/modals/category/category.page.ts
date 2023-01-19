import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ModalController } from '@ionic/angular';
import { Categories } from 'src/app/models/categories.model';
import { CategoriesService } from 'src/app/services/categories.service';
import { ReloadService } from 'src/app/services/reload.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.page.html',
  styleUrls: ['./category.page.scss'],
})
export class CategoryPage implements OnInit {

  categories: Categories[];

  constructor(public reloadService: ReloadService,
    private categoriesService: CategoriesService,
    private modalController: ModalController
  ) { }

  ngOnInit() {
    this.categoriesService.getCategories().subscribe((data: Categories[]) => {
      this.categories = data;
    });
  }


  newCategory: FormGroup = new FormGroup({
    title: new FormControl('')
  })

  saveCategory() {
    this.categoriesService.createCategory(this.newCategory.value)
      .subscribe(
        (response) => console.log(response),
        error => {
          console.error(error);
        });
    this.reloadService.reload();
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

