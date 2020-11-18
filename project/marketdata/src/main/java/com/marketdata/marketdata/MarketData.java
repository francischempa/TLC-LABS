package com.marketdata.marketdata;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MarketData {
    private int SELL_LIMIT;
    private double LAST_TRADED_PRICE;
    private String TICKER;
    private double ASK_PRICE;
    private int BUY_LIMIT;
    private int MAX_PRICE_SHIFT;
    private double BID_PRICE;

    public MarketData(){}

    public MarketData(int SELL_LIMIT, double LAST_TRADED_PRICE, String TICKER, double ASK_PRICE, int BUY_LIMIT, int MAX_PRICE_SHIFT, double BID_PRICE) {
        this.SELL_LIMIT = SELL_LIMIT;
        this.LAST_TRADED_PRICE = LAST_TRADED_PRICE;
        this.TICKER = TICKER;
        this.ASK_PRICE = ASK_PRICE;
        this.BUY_LIMIT = BUY_LIMIT;
        this.MAX_PRICE_SHIFT = MAX_PRICE_SHIFT;
        this.BID_PRICE = BID_PRICE;
    }

    @JsonProperty("SELL_LIMIT")
    public int getSELL_LIMIT() {
        return SELL_LIMIT;
    }

    @JsonProperty("LAST_TRADED_PRICE")
    public double getLAST_TRADED_PRICE() {
        return LAST_TRADED_PRICE;
    }

    @JsonProperty("TICKER")
    public String getTICKER() {
        return TICKER;
    }

    @JsonProperty("ASK_PRICE")
    public double getASK_PRICE() {
        return ASK_PRICE;
    }

    @JsonProperty("BUY_LIMIT")
    public int getBUY_LIMIT() {
        return BUY_LIMIT;
    }

    @JsonProperty("MAX_PRICE_SHIFT")
    public int getMAX_PRICE_SHIFT() {
        return MAX_PRICE_SHIFT;
    }

    @JsonProperty("BID_PRICE")
    public double getBID_PRICE() {
        return BID_PRICE;
    }

    public void setSELL_LIMIT(int SELL_LIMIT) {
        this.SELL_LIMIT = SELL_LIMIT;
    }

    public void setLAST_TRADED_PRICE(double LAST_TRADED_PRICE) {
        this.LAST_TRADED_PRICE = LAST_TRADED_PRICE;
    }

    public void setTICKER(String TICKER) {
        this.TICKER = TICKER;
    }

    public void setASK_PRICE(double ASK_PRICE) {
        this.ASK_PRICE = ASK_PRICE;
    }

    public void setBUY_LIMIT(int BUY_LIMIT) {
        this.BUY_LIMIT = BUY_LIMIT;
    }

    public void setMAX_PRICE_SHIFT(int MAX_PRICE_SHIFT) {
        this.MAX_PRICE_SHIFT = MAX_PRICE_SHIFT;
    }

    public void setBID_PRICE(double BID_PRICE) {
        this.BID_PRICE = BID_PRICE;
    }

    @Override
    public String toString() {
        return "MarketData{" +
                "SELL_LIMIT=" + SELL_LIMIT +
                ", LAST_TRADED_PRICE=" + LAST_TRADED_PRICE +
                ", TICKER='" + TICKER + '\'' +
                ", ASK_PRICE=" + ASK_PRICE +
                ", BUY_LIMIT=" + BUY_LIMIT +
                ", MAX_PRICE_SHIFT=" + MAX_PRICE_SHIFT +
                ", BID_PRICE=" + BID_PRICE +
                '}';
    }
}