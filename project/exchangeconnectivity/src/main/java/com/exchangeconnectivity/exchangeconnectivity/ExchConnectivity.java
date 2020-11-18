package com.exchangeconnectivity.exchangeconnectivity;

public class ExchConnectivity implements Comparable {
    private String id;

    public String getOrder

    public String getOrderBookQueueKey(){
        return "queue:te:" + id + ":orderbook";
    }

    public String getMakeOrderQueueKey(){
        return "queue:te:" + id + ":makeorder";
    }

    public ExchConnectivity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Object o) {
        ExchConnectivity other = (ExchConnectivity)o;
        return (Integer)this.id.compareTo(other.id);
    }
}
