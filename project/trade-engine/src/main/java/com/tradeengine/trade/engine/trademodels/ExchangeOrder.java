package com.tradeengine.trade.engine.trademodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeOrder implements Serializable,Blockable {
    private String id;
    private String product;
    private int quantity;
    private double price;
    private String side;
    private int cumulativeQuantity;
    private String exchange;

    public ExchangeOrder() {
    }

    public ExchangeOrder(String id,String product, int quantity, double price, String side, int cumulativeQuantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
        this.cumulativeQuantity = cumulativeQuantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public void setCumulativeQuantity(int cumulativeQuantity) {
        this.cumulativeQuantity = cumulativeQuantity;
    }

    public String getProduct() {
        return product;
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

    @JsonProperty("cumulatitiveQuantity")
    public int getCumulativeQuantity() {
        return cumulativeQuantity;
    }

    @Override
    public String toString() {
        return "ExchangeOrder{" +
                "product='" + product + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", side='" + side + '\'' +
                ", cumulativeQuantity=" + cumulativeQuantity +
                ", exchange='" + exchange + '\'' +
                '}';
    }

    @Override
    public String getOrderType() {
        return product+":"+side;
    }
}
