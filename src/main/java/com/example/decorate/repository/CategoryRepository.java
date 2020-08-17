package com.example.decorate.repository;

import com.example.decorate.domain.Category;
import com.example.decorate.domain.dto.CategoryListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

   /* @Query("SELECT new com.example.decorate.domain.dto.CategoryListItem(c.id,c.name,c.type) " +
            "FROM Category as c")
    List<CategoryListItem> listOfAllCategories();*/
}
