package com.investing.algoTrading.Tradex.model;

public class Position {

    private String product;
    private String exchange;
    private Double sellPrice;
    private int sellQuantity;
    private Double lastPrice;
    private String tradingSymbol;
    private int buyQuantity;
    private Double buyPrice;

    public String getProduct() {
        return product;
    }

    public String getExchange() {
        return exchange;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public int getSellQuantity() {
        return sellQuantity;
    }

    public Double getLastPrice() {
        return lastPrice;
    }

    public int getBuyQuantity() {
        return buyQuantity;
    }

    public String getTradingSymbol() {
        return tradingSymbol;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setSellQuantity(int sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public void setLastPrice(Double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public void setTradingSymbol(String tradingSymbol) {
        this.tradingSymbol = tradingSymbol;
    }

    public void setBuyQuantity(int buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

}
