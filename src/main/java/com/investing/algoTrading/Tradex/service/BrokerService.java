package com.investing.algoTrading.Tradex.service;


import com.investing.algoTrading.Tradex.model.KiteSession;
import com.investing.algoTrading.Tradex.model.Position;
import org.json.JSONObject;

import java.util.List;

public interface BrokerService {

    KiteSession createKiteConnectSession(String requestToken);
    void brokerLogout(String accessToken);
    List<Position> getPositions(String accessToken);
    void placeOrder();
    void modifyOrder();
    void cancelOrder();
}
