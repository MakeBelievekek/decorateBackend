package com.example.decorate.domain.dto;

public class Items {
    String Name;
    String Description;
    double Quantity;
    String Unit;
    double UnitPrice;
    double ItemTotal;
    String SKU;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double quantity) {
        Quantity = quantity;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public double getItemTotal() {
        return ItemTotal;
    }

    public void setItemTotal(double itemTotal) {
        ItemTotal = itemTotal;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    @Override
    public String toString() {
        return "Items{" +
                "Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", Quantity=" + Quantity +
                ", Unit='" + Unit + '\'' +
                ", UnitPrice=" + UnitPrice +
                ", ItemTotal=" + ItemTotal +
                ", SKU='" + SKU + '\'' +
                '}';
    }
}
