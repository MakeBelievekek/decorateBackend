package com.example.decorate.domain;

import com.example.decorate.domain.dto.ProductListItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;


@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String name;

    private Integer quantity;

    private Integer price;

    private String itemNumber;


    public Product(ProductListItem productListItem, Integer qty) {
        this.name = productListItem.getName();
        this.quantity = qty;
        this.price = productListItem.getPrice();
        this.itemNumber = productListItem.getItemNumber();
    }
}
