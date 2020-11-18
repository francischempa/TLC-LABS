package com.exchangeconnectivity.exchangeconnectivity.taskqueues;

import com.exchangeconnectivity.exchangeconnectivity.UtilsComet;
import com.exchangeconnectivity.exchangeconnectivity.exchangemodels.ExchangeOrder;
import com.exchangeconnectivity.exchangeconnectivity.exchangemodels.Order;
import reactor.core.publisher.Mono;
import redis.clients.jedis.Jedis;

public class MakeOrderQueue implements Runnable {
    private final Jedis jedis;

    public MakeOrderQueue() {
        this.jedis = new Jedis(UtilsComet.redisAddress);
    }
    @Override
    public void run() {
        while (true){
            String taskKey = UtilsComet.getFromQueue(UtilsComet.service.getMakeOrderQueueKey(),jedis);
            if(taskKey == null) continue;
            String orderString = UtilsComet.getFromQueue(taskKey,jedis);
            ExchangeOrder exchangeOrder = UtilsComet.convertToObject(orderString,ExchangeOrder.class);
            System.out.println(exchangeOrder);
            if(exchangeOrder.getPrice() == 0 && exchangeOrder.getQuantity() ==0){
                while (UtilsComet.getQueueLen(taskKey+":id",jedis) > 0){
                    System.out.println(UtilsComet.getFromQueue(taskKey+":id",jedis));
                }
                UtilsComet.deleteData(exchangeOrder.getOrderType(),jedis);
                System.out.println("Clean up done!");
//                while (true){}
                continue;
            }
            Order order =   new Order(exchangeOrder.getProduct(),exchangeOrder.getQuantity(),exchangeOrder.getPrice(),exchangeOrder.getSide());
            String orderId = UtilsComet.exchangeWebClient.post().uri(order.generateUri().toLowerCase()).body(Mono.just(order),Order.class).retrieve().bodyToMono(String.class).block();
            UtilsComet.addToQueue(taskKey+":id",orderId,jedis);
            System.out.println("Done !!!");


        }
    }
}
