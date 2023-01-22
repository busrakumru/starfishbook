package de.beuth.starfishbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.beuth.starfishbook.model.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {

}
