package com.investing.algoTrading.Tradex.service;


import com.investing.algoTrading.Tradex.model.KiteSession;
import com.investing.algoTrading.Tradex.model.Position;
import org.json.JSONObject;

import java.util.List;

public interface BrokerService {

    KiteSession createKiteConnectSession(String requestToken);
    void setToken(String authHeader);
    void brokerLogout();
    List<Position> getPositions();
    void placeOrder();
    void modifyOrder();
    void cancelOrder();
}
