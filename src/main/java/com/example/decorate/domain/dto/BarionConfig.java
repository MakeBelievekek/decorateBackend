package com.example.decorate.domain.dto;

import org.springframework.stereotype.Component;


public class BarionConfig {

    private String posKey ;
    private String redirectUrl;
    private String callbackUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getPosKey() {
        return posKey;
    }

    public void setPosKey(String posKey) {
        this.posKey = posKey;
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

    @Override
    public String toString() {
        return "BarionConfig{" +
                "posKey='" + posKey + '\'' +
                ", redirectUrl='" + redirectUrl + '\'' +
                ", callbackUrl='" + callbackUrl + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
