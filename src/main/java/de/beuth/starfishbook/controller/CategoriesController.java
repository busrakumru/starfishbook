package de.beuth.starfishbook.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.beuth.starfishbook.model.Categories;
import de.beuth.starfishbook.service.CategoriesService;

@CrossOrigin(origins = "https://localhost:8100")
@RestController
@RequestMapping("auth/users/")
public class CategoriesController {

    @Autowired
    private final CategoriesService categoriesService; 
  
    public CategoriesController(CategoriesService categoriesService) {
          this.categoriesService = categoriesService;
    }
    
    @PostMapping("categories")
    public Categories createCategory(@RequestBody Categories category) {
        return this.categoriesService.save(category);
    }

    @GetMapping("categories")
    public List<Categories> getCategories() {
    return this.categoriesService.getAllCategories(); 
   }

   @DeleteMapping("categories/{id}")
    public Boolean delete(@PathVariable Long id) {
        return this.categoriesService.delete(id);
    }

}
