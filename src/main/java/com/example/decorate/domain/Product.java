package com.example.decorate.domain;

import com.example.decorate.domain.dto.ProductListItem;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Embeddable
public class Product {

    private String name;

    private Integer quantity;

    private Integer price;

    private String itemNumber;

    @Column(name = "modified")
    private Instant modified;

    @Column(name = "created")
    private Instant created = Instant.now();

    public Product(ProductListItem productListItem, Integer qty) {
        this.name = productListItem.getName();
        this.quantity = qty;
        this.price = productListItem.getPrice();
        this.itemNumber = productListItem.getItemNumber();
        this.created = Instant.now();
    }

}
