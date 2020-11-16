package com.tradeengine.trade.engine;

import com.tradeengine.trade.engine.exchangeconnectivity.ExchConnectivity;

import java.util.ArrayList;
import java.util.List;

public class Config {

    public static int numberOfExchanges = 2;

    public static int numberOfTaskQueues = 4;

    public static List<ExchConnectivity> exchConnectivityList = new ArrayList<>();
    static {
        exchConnectivityList.add(new ExchConnectivity("exch1"));
        exchConnectivityList.add(new ExchConnectivity("exch2"));
    }

    //    QUEUES USED BY TRADE ENGINE
    private static String makeOrderQueueFromTeToEc1 = "queue:te:exch1:makeorder";
    private static String makeOrderQueueFromTeToEc2 = "queue:te:exch2:makeorder";
    private static String makeOrderQueueFromOvToTe = "queue:ov:te:makeorder";
    private static String orderBookQueueFromTeToEc1 = "queue:te:exch1:orderbook";
    private static String orderBookQueueFromTeToEc2 = "queue:te:exch2:orderbook";
    private static String orderMonitorQueue = "queue:te:ordermonitor";
    private static String cancelOrderQueue = "queue:te:exch1:cancelOrder";
    private static String orderBookResultsQueue = "queue:exch:te:orderbook";

    private static String redisAddress = "localhost";

    private static String orderBookBasesUrl = "https://exchange.matraining.com/orderbook";

    private static String BUY = "BUY";
    private static String SELL = "SELL";

    private static final  String exchange1 = "exch1";
    private static final String exchange2 = "exch2";

    public static String getExchange1() {
        return exchange1;
    }

    public static String getExchange2() {
        return exchange2;
    }

    public static String getBUY() {
        return BUY;
    }

    public static void setBUY(String BUY) {
        Config.BUY = BUY;
    }

    public static String getSELL() {
        return SELL;
    }

    public static void setSELL(String SELL) {
        Config.SELL = SELL;
    }

    public static String getMakeOrderQueueFromTeToEc1() {
        return makeOrderQueueFromTeToEc1;
    }

    public static void setMakeOrderQueueFromTeToEc1(String makeOrderQueueFromTeToEc1) {
        Config.makeOrderQueueFromTeToEc1 = makeOrderQueueFromTeToEc1;
    }

    public static String getMakeOrderQueueFromTeToEc2() {
        return makeOrderQueueFromTeToEc2;
    }

    public static void setMakeOrderQueueFromTeToEc2(String makeOrderQueueFromTeToEc2) {
        Config.makeOrderQueueFromTeToEc2 = makeOrderQueueFromTeToEc2;
    }

    public static String getMakeOrderQueueFromOvToTe() {
        return makeOrderQueueFromOvToTe;
    }

    public static void setMakeOrderQueueFromOvToTe(String makeOrderQueueFromOvToTe) {
        Config.makeOrderQueueFromOvToTe = makeOrderQueueFromOvToTe;
    }

    public static String getOrderBookResultsQueue() {
        return orderBookResultsQueue;
    }

    public static void setOrderBookResultsQueue(String orderBookResultsQueue) {
        Config.orderBookResultsQueue = orderBookResultsQueue;
    }

    public static String getOrderBookBasesUrl() {
        return orderBookBasesUrl;
    }

    public static void setOrderBookBasesUrl(String orderBookBasesUrl) {
        Config.orderBookBasesUrl = orderBookBasesUrl;
    }

    public static String getRedisAddress() {
        return redisAddress;
    }

    public static void setRedisAddress(String redisAddress) {
        Config.redisAddress = redisAddress;
    }

    public static int getNumberOfTaskQueues() {
        return numberOfTaskQueues;
    }

    public static void setNumberOfTaskQueues(int numberOfTaskQueues) {
        Config.numberOfTaskQueues = numberOfTaskQueues;
    }

    public static String getOrderBookQueueFromTeToEc1() {
        return orderBookQueueFromTeToEc1;
    }

    public static void setOrderBookQueueFromTeToEc1(String orderBookQueueFromTeToEc1) {
        Config.orderBookQueueFromTeToEc1 = orderBookQueueFromTeToEc1;
    }

    public static String getOrderBookQueueFromTeToEc2() {
        return orderBookQueueFromTeToEc2;
    }

    public static void setOrderBookQueueFromTeToEc2(String orderBookQueueFromTeToEc2) {
        Config.orderBookQueueFromTeToEc2 = orderBookQueueFromTeToEc2;
    }

    public static String getOrderMonitorQueue() {
        return orderMonitorQueue;
    }

    public static void setOrderMonitorQueue(String orderMonitorQueue) {
        Config.orderMonitorQueue = orderMonitorQueue;
    }

    public static String getCancelOrderQueue() {
        return cancelOrderQueue;
    }

    public static void setCancelOrderQueue(String cancelOrderQueue) {
        Config.cancelOrderQueue = cancelOrderQueue;
    }
}

