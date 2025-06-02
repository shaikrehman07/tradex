package com.investing.algoTrading.Tradex.brokerConfig;

import com.investing.algoTrading.Tradex.model.KiteSession;
import com.investing.algoTrading.Tradex.service.KiteConnectServiceImp;
import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KiteConnectConfigurationImp implements KiteConnectConfiguration {

    @Value("${kite.apiKey}")
    private String apiKey;

    @Value("${kite.apiSecret}")
    private String apiSecret;

    private final KiteSessionManager kiteSessionManager;

    public KiteConnectConfigurationImp(KiteSessionManager kiteSessionManager){
        this.kiteSessionManager = kiteSessionManager;
    }

    @Override
    public KiteSession createKiteConnectSession(String requestToken) {

        try {

            KiteConnect kiteConnect = new KiteConnect(this.apiKey);
            User user = kiteConnect.generateSession(requestToken, apiSecret);

            KiteSession sessionResponse = new KiteSession();
            sessionResponse.setLoginStatus(true);
            sessionResponse.setAccessToken(user.accessToken);
            //kiteConnect.setPublicToken(user.publicToken);

            return sessionResponse;

        } catch (IOException | KiteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public KiteConnect getKiteConnectInstance(String accessToken){
        return kiteSessionManager.getOrCreateSession(accessToken, apiKey);
    }

}
