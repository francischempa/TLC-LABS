package com.exchangeconnectivity.exchangeconnectivity.exchangemodels;

import com.exchangeconnectivity.exchangeconnectivity.UtilsComet;

import java.io.Serializable;

public class Order implements Serializable,Blockable {
    private String id;
    private String product;
    private int quantity;
    private double price;
    private String side;

    public Order(String id, String product, int quantity, double price, String side) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
    }

    public Order() {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String generateUri(){
        return "/" + UtilsComet.API_KEY + "/order";
    }
    @Override
    public String getOrderType() {
        return product+":"+side;
    }


}
