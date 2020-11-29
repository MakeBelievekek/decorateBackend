package com.example.decorate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseTransaction {

    private String POSTransactionId;

    private String TransactionId;

    private String Status;

    private String Currency;

    private String TransactionTime;

    private String RelatedId;
}
