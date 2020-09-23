package com.example.decorate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTransaction {
    String POSTransactionId;
    String TransactionId;
    String Status;
    String Currency;
    String TransactionTime;
    String RelatedId;
}
