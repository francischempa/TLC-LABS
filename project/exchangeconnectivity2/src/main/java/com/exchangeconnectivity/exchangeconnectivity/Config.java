package com.exchangeconnectivity.exchangeconnectivity;

public class Config {

    public static ExchConnectivity service = new ExchConnectivity("exch2");

    private static String exchangeName = "exch2";
    public static int numberOfTaskQueues = 4;

//    QUEUES USED BY EXCHANGE CONNECTIVITY
    private static String makeOrderQueue = "queue:te:exch2:makeorder";
    private static String orderBookQueue = "queue:te:exch2:orderbook";
    private static String orderMonitorQueue = "queue:te:exch2:ordermonitor";
    private static String cancelOrderQueue = "queue:te:exch2:cancelOrder";

    private static String orderBookResultsQueue = "queue:exch:te:orderbook";

    private static String redisAddress = "localhost";

    private static String orderBookBasesUrl = "https://exchange.matraining.com";

    public static String getExchangeName() {
        return exchangeName;
    }

    public static void setExchangeName(String exchangeName) {
        Config.exchangeName = exchangeName;
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

    public static String getMakeOrderQueue() {
        return makeOrderQueue;
    }

    public static void setMakeOrderQueue(String makeOrderQueue) {
        Config.makeOrderQueue = makeOrderQueue;
    }

    public static String getOrderBookQueue() {
        return orderBookQueue;
    }

    public static void setOrderBookQueue(String orderBookQueue) {
        Config.orderBookQueue = orderBookQueue;
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
