package com.example.decorate.domain.dto;

import java.util.List;

public class Transactions {
    String POSTransactionId;
    String Payee;
    double Total;
    List<Items> Items;

    public String getPOSTransactionId() {
        return POSTransactionId;
    }

    public void setPOSTransactionId(String POSTransactionId) {
        this.POSTransactionId = POSTransactionId;
    }

    public String getPayee() {
        return Payee;
    }

    public void setPayee(String payee) {
        Payee = payee;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public List<com.example.decorate.domain.dto.Items> getItems() {
        return Items;
    }

    public void setItems(List<com.example.decorate.domain.dto.Items> items) {
        Items = items;
    }
}
