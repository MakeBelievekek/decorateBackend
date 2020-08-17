package com.example.decorate.service;

import com.example.decorate.domain.dto.CategoryListItem;
import com.example.decorate.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

  /*  public List<CategoryListItem> getAll() {

        return categoryRepository.listOfAllCategories();
    }*/
}
