package com.tradeengine.trade.engine.trademodels;

import java.util.List;

public class OrderBookTaskResponse {
    private String exchangeName;
    private List<ExchangeOrder> exchangeOrderList;

    public OrderBookTaskResponse() {
    }

    public OrderBookTaskResponse(String exchangeName, List<ExchangeOrder> exchangeOrderList) {
        this.exchangeName = exchangeName;
        this.exchangeOrderList = exchangeOrderList;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public List<ExchangeOrder> getExchangeOrderList() {
        return exchangeOrderList;
    }

    public void setExchangeOrderList(List<ExchangeOrder> exchangeOrderList) {
        this.exchangeOrderList = exchangeOrderList;
    }
}
