package com.investing.algoTrading.Tradex.service;

import com.zerodhatech.kiteconnect.KiteConnect;

public interface KiteConnectService extends BrokerService{
    KiteConnect getKiteConnectInstance();
}
