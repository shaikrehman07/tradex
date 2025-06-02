package com.investing.algoTrading.Tradex.brokerConfig;

import com.investing.algoTrading.Tradex.model.KiteSession;
import com.zerodhatech.kiteconnect.KiteConnect;
import org.json.JSONObject;

public interface KiteConnectConfiguration {

    KiteSession createKiteConnectSession(String requestToken);
    KiteConnect getKiteConnectInstance(String accessToken);
}
