package com.example.decorate.domain.dto;

import com.example.decorate.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Items {

    private String Name;

    private String Description;

    private int Quantity;

    private String Unit;

    private int UnitPrice;

    private int ItemTotal;

    private String SKU;

    public Items(ProductListItem productListItem, int qty) {
        this.Name = productListItem.getName();
        this.Description = productListItem.getProductDesc();
        this.Quantity = qty;
        this.Unit = productListItem.getTypeOfSize();
        this.UnitPrice = productListItem.getPrice();
        this.ItemTotal = productListItem.getPrice() * qty;
        this.SKU = productListItem.getItemNumber();
    }
    public Items(Product product) {
        this.Name = product.getName();
        this.Description = product.getProductDesc();
        this.Quantity = product.getQuantity();
        this.Unit = product.getTypeOfSize();
        this.UnitPrice = product.getPrice();
        this.ItemTotal = product.getPrice() * product.getQuantity();
        this.SKU = product.getItemNumber();
    }
}
