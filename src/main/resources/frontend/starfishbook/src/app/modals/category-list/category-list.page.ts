import { Component, OnInit } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { Categories } from 'src/app/models/categories.model';
import { CategoriesService } from 'src/app/services/categories.service';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.page.html',
  styleUrls: ['./category-list.page.scss'],
})
export class CategoryListPage implements OnInit {

  constructor(private categoriesService: CategoriesService,
    private modalController: ModalController) { }

  categories: Categories[];

  ngOnInit() {

    this.categoriesService.getCategories().subscribe((data: Categories[]) => {
      this.categories = data;
      console.log(data);
    });
  }

  passId(c) {

    console.log(c.id);
    this.modalController.dismiss({
      id: c.id,
      title: c.title
    });
  }

  closeModal() {
    this.modalController.dismiss();
  }
}
