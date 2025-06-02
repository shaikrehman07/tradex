package com.investing.algoTrading.Tradex.brokerConfig;

import com.zerodhatech.kiteconnect.KiteConnect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class KiteSessionManager {

    private final Map<String, KiteConnect> sessionMap = new ConcurrentHashMap<>();

    public KiteConnect getOrCreateSession(String accessToken, String apiKey) {
        return sessionMap.computeIfAbsent(accessToken, token -> {
            KiteConnect kc = new KiteConnect(apiKey);
            kc.setAccessToken(token);
            return kc;
        });
    }

    public void invalidateSession(String accessToken) {
        sessionMap.remove(accessToken);
    }
}
