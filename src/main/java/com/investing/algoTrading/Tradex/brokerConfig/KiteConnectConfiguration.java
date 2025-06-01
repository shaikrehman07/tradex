package com.investing.algoTrading.Tradex.brokerConfig;

import com.zerodhatech.kiteconnect.KiteConnect;

public interface KiteConnectConfiguration {

    String getLoginURL();
    boolean createKiteConnectSession(String requestToken);
    void setAccessToken(String accessToken);
    KiteConnect getKiteConnectInstance();
}
