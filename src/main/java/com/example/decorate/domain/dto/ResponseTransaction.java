package com.example.decorate.domain.dto;

public class ResponseTransaction {
    String posTransactionId;
    String transactionId;
    String status;
    String currency;
    String transactionTime;
    String relatedId;

    public String getPosTransactionId() {
        return posTransactionId;
    }

    public void setPosTransactionId(String posTransactionId) {
        this.posTransactionId = posTransactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(String relatedId) {
        this.relatedId = relatedId;
    }
}
