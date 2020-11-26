package com.example.decorate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseBodyDto {

    private String paymentId;

    private String PaymentRequestId;

    private String Status;

    private String QRUrl;

    private List<ResponseTransaction> Transactions;

    private String RecurrenceResult;

    private String GatewayUrl;

    private String RedirectUrl;

    private String CallbackUrl;

    private List<String> Errors;
}
