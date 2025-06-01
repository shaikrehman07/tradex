package com.investing.algoTrading.Tradex.brokerConfig;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KiteConnectConfigurationImp implements KiteConnectConfiguration {

    private final KiteConnect kiteConnect;

    @Value("${kite.apiSecret}")
    private String apiSecret;

    public static final String EMPTY_STRING = "";

    public KiteConnectConfigurationImp(@Value("${kite.apiKey}") String apiKey) {
        this.kiteConnect = new KiteConnect(apiKey);
    }

    @Override
    public String getLoginURL() {
        return this.kiteConnect.getLoginURL();
    }

    @Override
    public boolean createKiteConnectSession(String requestToken) {

        try {
            String apiSecret = this.apiSecret;
            User user = this.kiteConnect.generateSession(requestToken, apiSecret);

            this.setAccessToken(user.accessToken);
            this.kiteConnect.setPublicToken(user.publicToken);

        } catch (IOException | KiteException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public void setAccessToken(String accessToken){
        this.kiteConnect.setAccessToken(accessToken);
    }

    @Override
    public KiteConnect getKiteConnectInstance(){
        return this.kiteConnect;
    }

}
