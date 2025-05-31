package com.investing.algoTrading.Tradex.service;


import com.investing.algoTrading.Tradex.model.Position;

import java.util.List;

public interface BrokerService {

    String getLoginURL();
    boolean createKiteConnectSession(String requestToken);
    void brokerLogout();
    List<Position> getPositions();
    void placeOrder();
    void modifyOrder();
    void cancelOrder();
}
