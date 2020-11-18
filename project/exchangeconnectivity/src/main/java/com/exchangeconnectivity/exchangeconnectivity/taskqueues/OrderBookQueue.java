package com.exchangeconnectivity.exchangeconnectivity.taskqueues;

import com.exchangeconnectivity.exchangeconnectivity.UtilsComet;
import com.exchangeconnectivity.exchangeconnectivity.tasks.OrderBookRequest;
import com.exchangeconnectivity.exchangeconnectivity.exchangemodels.ExchangeOrder;
import redis.clients.jedis.Jedis;

public class OrderBookQueue implements Runnable {
    private final Jedis jedis;

    public OrderBookQueue() {
        this.jedis = new Jedis(UtilsComet.redisAddress);
    }
    @Override
    public void run() {
        while (true){
            String orderId = UtilsComet.getFromQueue(UtilsComet.service.getOrderBookQueueKey(),jedis);
            if(orderId == null) continue;
            String orderBookTaskRequest = UtilsComet.getCacheValue(orderId,jedis);
            UtilsComet.deleteData(orderId,jedis);

            if(orderBookTaskRequest==null){
//                ORDER POSSIBLY DELETED WHILES IN QUEUE
                continue;
            }
            OrderBookRequest orderBookRequest = UtilsComet.convertToObject(orderBookTaskRequest, OrderBookRequest.class);
            ExchangeOrder[] exchangeOrders = UtilsComet.exchangeWebClient.get().uri(orderBookRequest.generatePath().toLowerCase()).retrieve().bodyToMono(ExchangeOrder[].class).block();

            if(exchangeOrders == null) exchangeOrders = new ExchangeOrder[]{};
            for (ExchangeOrder exchangeOrder : exchangeOrders) {
                exchangeOrder.setExchange(UtilsComet.service.getId());
            }
            String data = UtilsComet.convertToString(exchangeOrders);
            UtilsComet.addToQueue(orderBookRequest.getId()+"orderbook",data,jedis); //LIST
//            while (true){}
        }
    }
}
//    CAP theorem