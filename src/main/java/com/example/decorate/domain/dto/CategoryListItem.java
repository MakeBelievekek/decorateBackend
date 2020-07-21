package com.example.decorate.domain.dto;

import com.example.decorate.domain.Category;

public class CategoryListItem {

    Long id;
    String name;
    String type;

    public CategoryListItem(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.type = category.getType();
    }

    public CategoryListItem(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
