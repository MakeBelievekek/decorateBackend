package com.example.decorate.domain.dto;

import com.example.decorate.domain.ShippingDetails;

import java.util.List;

public class PaymentData {
    String POSKey;
    String PaymentType;
    String PaymentWindow;
    boolean GuestCheckout;
    String FundingSources[];
    String PaymentRequestId;
    String OrderNumber;
    String PayerHint;
    ShippingDetails ShippingAddress;
    String RedirectUrl;
    String CallbackUrl;
    String Locale;
    String Currency;
    List<Transactions> Transactions;

    public String getPOSKey() {
        return POSKey;
    }

    public void setPOSKey(String POSKey) {
        this.POSKey = POSKey;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public String getPaymentWindow() {
        return PaymentWindow;
    }

    public void setPaymentWindow(String paymentWindow) {
        PaymentWindow = paymentWindow;
    }

    public boolean isGuestCheckout() {
        return GuestCheckout;
    }

    public void setGuestCheckout(boolean guestCheckout) {
        GuestCheckout = guestCheckout;
    }

    public String[] getFundingSources() {
        return FundingSources;
    }

    public void setFundingSources(String[] fundingSources) {
        FundingSources = fundingSources;
    }

    public String getPaymentRequestId() {
        return PaymentRequestId;
    }

    public void setPaymentRequestId(String paymentRequestId) {
        PaymentRequestId = paymentRequestId;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public String getPayerHint() {
        return PayerHint;
    }

    public void setPayerHint(String payerHint) {
        PayerHint = payerHint;
    }

    public ShippingDetails getShippingDetails() {
        return ShippingAddress;
    }

    public void setShippingDetails(ShippingDetails ShippingAddress) {
        ShippingAddress = ShippingAddress;
    }

    public String getRedirectUrl() {
        return RedirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        RedirectUrl = redirectUrl;
    }

    public String getCallbackUrl() {
        return CallbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        CallbackUrl = callbackUrl;
    }

    public String getLocale() {
        return Locale;
    }

    public void setLocale(String locale) {
        Locale = locale;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public List<com.example.decorate.domain.dto.Transactions> getTransactions() {
        return Transactions;
    }

    public void setTransactions(List<com.example.decorate.domain.dto.Transactions> transactions) {
        Transactions = transactions;
    }
}
