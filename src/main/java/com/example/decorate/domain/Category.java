package com.example.decorate.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CategoryType categoryType;

    @OneToMany(mappedBy = "category")
    private List<CategoryListItem> categoryListItemList;


}
