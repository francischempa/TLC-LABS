package com.tradeengine.trade.engine.trademodels;

import java.io.Serializable;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    public  String id;
    public  String product;
    public  int quantity;
    public  double price;
    public  String side;
    public int tries=0;
    public String time="";
}
