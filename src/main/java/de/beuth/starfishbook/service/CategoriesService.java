package de.beuth.starfishbook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.beuth.starfishbook.model.Categories;
import de.beuth.starfishbook.repository.CategoriesRepository;

@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }


    public Long save(Categories request) {
        Categories category = new Categories();
        category.setTitle(request.getTitle());

        return categoriesRepository.save(category).getId();
    }

    public List<Categories> getAllCategories() {
        List<Categories> allCategories = new ArrayList<>();
        categoriesRepository.findAll().forEach(allCategories::add);
        return allCategories;
    }

    public Boolean delete(Long id) {
        this.categoriesRepository.deleteById(id);
        return this.categoriesRepository.existsById(id);
    }
}
