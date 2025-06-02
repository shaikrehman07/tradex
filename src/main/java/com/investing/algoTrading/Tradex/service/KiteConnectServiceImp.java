package com.investing.algoTrading.Tradex.service;

import com.investing.algoTrading.Tradex.brokerConfig.KiteConnectConfiguration;
import com.investing.algoTrading.Tradex.model.KiteSession;
import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.investing.algoTrading.Tradex.model.Position;
import com.zerodhatech.kiteconnect.utils.Constants;
import com.zerodhatech.models.Order;
import com.zerodhatech.models.OrderParams;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class KiteConnectServiceImp implements BrokerService{

    private final KiteConnectConfiguration kiteConnectConfiguration;

    public KiteConnectServiceImp(KiteConnectConfiguration kiteConnectConfiguration){
        this.kiteConnectConfiguration = kiteConnectConfiguration;
    }

    @Override
    public KiteSession createKiteConnectSession(String requestToken) {
        return kiteConnectConfiguration.createKiteConnectSession(requestToken);
    }

    private String getToken(String authHeader){
        return authHeader.substring(7);
    }

    @Override
    public void brokerLogout(String authHeader){
        try {
            String token = getToken(authHeader);
            KiteConnect kiteConnect = kiteConnectConfiguration.getKiteConnectInstance(token);
            kiteConnect.logout();
        }catch (IOException | KiteException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Position> getPositions(String authHeader) {
        try {
            String token = getToken(authHeader);
            KiteConnect kiteConnect = kiteConnectConfiguration.getKiteConnectInstance(token);
            Map<String, List<com. zerodhatech. models.Position>> positions = kiteConnect.getPositions();
            List<com. zerodhatech. models.Position> kitePositions =  positions.get("net");

            return kitePositions.stream()
                    .map(kitePosition -> {
                        Position position = new Position();
                        position.setTradingSymbol(kitePosition.tradingSymbol);
                        position.setBuyPrice(kitePosition.buyPrice);
                        position.setExchange(kitePosition.exchange);
                        position.setBuyQuantity(kitePosition.buyQuantity);
                        position.setProduct(kitePosition.product);
                        position.setLastPrice(kitePosition.lastPrice);
                        position.setSellPrice(kitePosition.sellPrice);
                        position.setSellQuantity(kitePosition.sellQuantity);
                        return position;
                    })
                    .toList();
        } catch (IOException | KiteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void placeOrder() {
//        try {
//            OrderParams orderParams = new OrderParams();
//            orderParams.quantity = 1;
//            orderParams.orderType = Constants.ORDER_TYPE_LIMIT;
//            orderParams.tradingsymbol = "ASHOKLEY";
//            orderParams.product = Constants.PRODUCT_CNC;
//            orderParams.exchange = Constants.EXCHANGE_NSE;
//            orderParams.transactionType = Constants.TRANSACTION_TYPE_BUY;
//            orderParams.validity = Constants.VALIDITY_DAY;
//            orderParams.price = 122.2;
//            orderParams.triggerPrice = 0.0;
//
//            KiteConnect kiteConnect = kiteConnectConfiguration.getKiteConnectInstance();
//            Order order = kiteConnect.placeOrder(orderParams, Constants.VARIETY_REGULAR);
//            System.out.println(order.orderId);
//        }catch(IOException | KiteException e){
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void modifyOrder() {
//        try {
//            OrderParams orderParams = new OrderParams();
//            orderParams.quantity = 1;
//            orderParams.orderType = Constants.ORDER_TYPE_LIMIT;
//            orderParams.tradingsymbol = "ASHOKLEY";
//            orderParams.product = Constants.PRODUCT_CNC;
//            orderParams.exchange = Constants.EXCHANGE_NSE;
//            orderParams.transactionType = Constants.TRANSACTION_TYPE_BUY;
//            orderParams.validity = Constants.VALIDITY_DAY;
//            orderParams.price = 122.2;
//            orderParams.triggerPrice = 0.0;
//
//            KiteConnect kiteConnect = kiteConnectConfiguration.getKiteConnectInstance();
//
//            Order order = kiteConnect.modifyOrder("180116000984900", orderParams, Constants.VARIETY_REGULAR);
//            System.out.println(order.orderId);
//
//        }catch(IOException | KiteException e){
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void cancelOrder() {
//        try {
//            KiteConnect kiteConnect = kiteConnectConfiguration.getKiteConnectInstance();
//            Order order2 = kiteConnect.cancelOrder("180116000727266", Constants.VARIETY_REGULAR);
//            System.out.println(order2.orderId);
//        }catch(IOException | KiteException e){
//            throw new RuntimeException(e);
//        }
    }

}
