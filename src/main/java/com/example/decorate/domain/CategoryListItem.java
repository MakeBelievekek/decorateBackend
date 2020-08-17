package com.example.decorate.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CategoryListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "key_id")
    private KeyHolder keyHolder;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public CategoryListItem() {
    }

    public CategoryListItem(KeyHolder keyHolder, Category category) {
        this.keyHolder = keyHolder;
        this.category = category;
    }

}
