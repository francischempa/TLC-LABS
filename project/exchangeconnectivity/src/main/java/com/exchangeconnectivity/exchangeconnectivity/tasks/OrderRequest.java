package com.exchangeconnectivity.exchangeconnectivity.tasks;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderRequest {
    private String id;
    private String orderid;

    public OrderRequest() {
    }

    public OrderRequest(String id, String orderid) {
        this.id = id;
        this.orderid = orderid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "CancelOrderRequest{" +
                "id='" + id + '\'' +
                ", orderid='" + orderid + '\'' +
                '}';
    }
}
