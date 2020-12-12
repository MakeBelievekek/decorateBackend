package com.example.decorate.domain;

import com.example.decorate.domain.dto.ProductListItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class Product {

    private String name;

    private Integer quantity;

    private Integer price;

    private String itemNumber;

    @Column(name = "time_stamp")
    private Instant timeStamp;

    public Product(ProductListItem productListItem, Integer qty) {
        this.name = productListItem.getName();
        this.quantity = qty;
        this.price = productListItem.getPrice();
        this.itemNumber = productListItem.getItemNumber();
        this.timeStamp = Instant.now();
    }

}
