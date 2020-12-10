package com.exchangeconnectivity.exchangeconnectivity.tasks;

import com.exchangeconnectivity.exchangeconnectivity.exchangemodels.ExchangeOrder;

public class MonitorRequest{
    private Long transaction_id;
    private String order_key;
    public ExchangeOrder exchangeOrder;

    public MonitorRequest() {
    }

    public MonitorRequest(Long transaction_id, String order_key, ExchangeOrder exchangeOrder) {
        this.transaction_id = transaction_id;
        this.order_key = order_key;
        this.exchangeOrder = exchangeOrder;
    }

    public String getOrder_key() {
        return order_key;
    }

    public void setOrder_key(String order_key) {
        this.order_key = order_key;
    }

    public Long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Long transaction_id) {
        this.transaction_id = transaction_id;
    }

    public ExchangeOrder getExchangeOrder() {
        return exchangeOrder;
    }

    public void setExchangeOrder(ExchangeOrder exchangeOrder) {
        this.exchangeOrder = exchangeOrder;
    }
}
