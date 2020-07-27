package com.example.decorate.domain.dto;

import java.util.List;

public class ResponseBodyDto {
    String paymentId;
    String paymentRequestId;
    String status;
    String qrurl;
    List<ResponseTransaction> transactions;
    String recurrenceResult;
    String gatewayUrl;
    String redirectUrl;
    String callbackUrl;
    List<String> errors;

    public ResponseBodyDto() {
    }

    public ResponseBodyDto(String paymentId, String paymentRequestId, String status, String qrurl, List<ResponseTransaction> transactions, String recurrenceResult, String gatewayUrl, String redirectUrl, String callbackUrl, List<String> errors) {
        this.paymentId = paymentId;
        this.paymentRequestId = paymentRequestId;
        this.status = status;
        this.qrurl = qrurl;
        this.transactions = transactions;
        this.recurrenceResult = recurrenceResult;
        this.gatewayUrl = gatewayUrl;
        this.redirectUrl = redirectUrl;
        this.callbackUrl = callbackUrl;
        this.errors = errors;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(String paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQrurl() {
        return qrurl;
    }

    public void setQrurl(String qrurl) {
        this.qrurl = qrurl;
    }

    public List<ResponseTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<ResponseTransaction> transactions) {
        this.transactions = transactions;
    }

    public String getRecurrenceResult() {
        return recurrenceResult;
    }

    public void setRecurrenceResult(String recurrenceResult) {
        this.recurrenceResult = recurrenceResult;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
