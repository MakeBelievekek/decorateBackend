package com.example.decorate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transactions {

    private String POSTransactionId;

    private String Payee;

    private double Total;

    private List<Items> Items;
}
