package com.tradeengine.trade.engine.trademodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidatedOrder implements Serializable,Blockable {
    private  String id;
    private  String product;
    private  int quantity;
    private  double price;
    private  String side;
    private int tries=0;
    private String time="";

    public ValidatedOrder() {
    }

    public ValidatedOrder(String id, String product, int quantity, double price, String side) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }


    public String getProduct() {
        return product;
    }


    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public int getTries() {
        return tries;
    }


    public void setTries(int tries) {
        this.tries = tries;
    }


    public int getQuantity() {
        return quantity;
    }


    public double getPrice() {
        return price;
    }

    public String getSide() {
        return side;
    }

    @Override
    public String toString() {
        return "ValidatedOrder{" +
                "id='" + id + '\'' +
                ", product='" + product + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", side='" + side + '\'' +
                ", tries=" + tries +
                ", time='" + time + '\'' +
                '}';
    }

    @Override
    public String getOrderType() {
        return product+":"+side;
    }
}
