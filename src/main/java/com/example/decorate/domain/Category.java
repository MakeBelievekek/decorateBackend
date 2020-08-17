/*
package com.example.decorate.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "prod_type")
    private ProductType productType;

    @OneToMany(mappedBy = "category")
    private List<CategoryListItem> categoryListItemList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public List<CategoryListItem> getCategoryListItemList() {
        return categoryListItemList;
    }

    public void setCategoryListItemList(List<CategoryListItem> categoryListItemList) {
        this.categoryListItemList = categoryListItemList;
    }
}
*/
