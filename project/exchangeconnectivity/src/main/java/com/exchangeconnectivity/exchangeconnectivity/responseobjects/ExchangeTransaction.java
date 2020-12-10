package com.exchangeconnectivity.exchangeconnectivity.responseobjects;

public class ExchangeTransaction {
    private Long transaction_id;
    private Long order_id;
    private String exchange_id;
    private String order_key;
    private Double stock_price;
    private Integer quantity;
    private String transaction_status;

    public ExchangeTransaction() {
    }

    public ExchangeTransaction(Long transaction_id, Long order_id, String exchange_id, String order_key, Double stock_price, Integer quantity, String transaction_status) {
        this.transaction_id = transaction_id;
        this.order_id = order_id;
        this.exchange_id = exchange_id;
        this.order_key = order_key;
        this.stock_price = stock_price;
        this.quantity = quantity;
        this.transaction_status = transaction_status;
    }

    public Long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Long transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public String getExchange_id() {
        return exchange_id;
    }

    public void setExchange_id(String exchange_id) {
        this.exchange_id = exchange_id;
    }

    public String getOrder_key() {
        return order_key;
    }

    public void setOrder_key(String order_key) {
        this.order_key = order_key;
    }

    public Double getStock_price() {
        return stock_price;
    }

    public void setStock_price(Double stock_price) {
        this.stock_price = stock_price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getTransaction_status() {
        return transaction_status;
    }

    public void setTransaction_status(String transaction_status) {
        this.transaction_status = transaction_status;
    }
}
