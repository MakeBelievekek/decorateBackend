package com.example.decorate.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaymentData {
    String POSKey;
    String PaymentType;
    String PaymentWindow;
    boolean GuestCheckout;
    String FundingSources[];
    String PaymentRequestId;
    String OrderNumber;
    String PayerHint;
    ShippingOrder ShippingAddress;
    String RedirectUrl;
    String CallbackUrl;
    String Locale;
    String Currency;
    List<Transactions> Transactions;


}
