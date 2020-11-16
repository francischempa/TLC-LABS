package com.exchangeconnectivity.exchangeconnectivity.tasks;

import java.io.Serializable;

public class OrderBookTask implements Serializable {
    private String id;
    private String product;
    private String side;

    public OrderBookTask() {
    }

    public OrderBookTask(String id, String product, String side) {
        this.id = id;
        this.product = product;
        this.side = side;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String generatePath(){
        String otherSide = "buy";
        if(side.toLowerCase().equals("buy")) otherSide = "sell";
        return "/orderbook/" + product + "/" + otherSide;
    }
}
