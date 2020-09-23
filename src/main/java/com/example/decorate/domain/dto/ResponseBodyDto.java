package com.example.decorate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBodyDto {
    String paymentId;
    String PaymentRequestId;
    String Status;
    String QRUrl;
    List<ResponseTransaction> Transactions;
    String RecurrenceResult;
    String GatewayUrl;
    String RedirectUrl;
    String CallbackUrl;
    List<String> Errors;


}
