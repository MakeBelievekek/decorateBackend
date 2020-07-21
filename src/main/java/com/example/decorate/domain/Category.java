package com.example.decorate.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "category")
    private List<ProductCategoryListItem> productCategoryListItemList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductCategoryListItem> getProductCategoryListItemList() {
        return productCategoryListItemList;
    }

    public void setProductCategoryListItemList(List<ProductCategoryListItem> productCategoryListItemList) {
        this.productCategoryListItemList = productCategoryListItemList;
    }
}
