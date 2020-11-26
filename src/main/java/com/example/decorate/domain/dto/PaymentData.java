package com.example.decorate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentData {

    private String POSKey;

    private String PaymentType;

    private String PaymentWindow;

    private boolean GuestCheckout;

    private String FundingSources[];

    private String PaymentRequestId;

    private String OrderNumber;

    private String PayerHint;

    private ShippingOrder ShippingAddress;

    private String RedirectUrl;

    private String CallbackUrl;

    private String Locale;

    private String Currency;

    private List<Transactions> Transactions;
}
