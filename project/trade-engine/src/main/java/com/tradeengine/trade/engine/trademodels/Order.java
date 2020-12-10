package com.tradeengine.trade.engine.trademodels;

public class Order {
    public  String id;
    public  String product;
    public  int quantity;
    public  double price;
    public  String side;
    public int tries=0;
    public String time="";

    public Order(String id, String product, int quantity, double price, String side, int tries, String time) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
        this.tries = tries;
        this.time = time;
    }
}
