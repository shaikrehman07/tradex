package com.investing.algoTrading.Tradex.model;

public class KiteSession {

    private Boolean loginStatus = false;
    private String accessToken;

    public Boolean getLoginStatus() {
        return loginStatus;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setLoginStatus(Boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
