package com.exchangeconnectivity.exchangeconnectivity.taskqueues;

import com.exchangeconnectivity.exchangeconnectivity.UtilsComet;
import com.exchangeconnectivity.exchangeconnectivity.exchangemodels.ExchangeOrder;
import com.exchangeconnectivity.exchangeconnectivity.exchangemodels.Order;
import com.exchangeconnectivity.exchangeconnectivity.responseobjects.ExchangeTransaction;
import com.exchangeconnectivity.exchangeconnectivity.tasks.MonitorRequest;
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
            if(exchangeOrder == null) continue;
            System.out.println("#MAKE ORDER QUEUE ");
            System.out.println(exchangeOrder);
            if(exchangeOrder.getPrice() == 0 && exchangeOrder.getQuantity() ==0){
                while (UtilsComet.getQueueLen(taskKey+":id",jedis) > 0){
                    System.out.println(UtilsComet.getFromQueue(taskKey+":id",jedis));
                }
                UtilsComet.deleteData(exchangeOrder.getOrderType(),jedis);
                System.out.println("Clean up done!");
                continue;
            }
            Order order =   new Order(exchangeOrder.getId(),exchangeOrder.getProduct(),exchangeOrder.getQuantity(),exchangeOrder.getPrice(),exchangeOrder.getSide());
            String orderId = UtilsComet.exchangeWebClient.post().uri(order.generateUri().toLowerCase()).body(Mono.just(order),Order.class).retrieve().bodyToMono(String.class).block();
            orderId = UtilsComet.convertToObject(orderId,String.class);
            if(isSuccess(orderId)){
//                SAVE TRANSACTION
                Long transaction_id = 1L;
                ExchangeTransaction exchangeTransaction = new ExchangeTransaction(transaction_id,Long.parseLong(exchangeOrder.getId()),UtilsComet.service.getId(),orderId,exchangeOrder.getPrice(),exchangeOrder.getQuantity(),"placed successfully");

                UtilsComet.addToQueue(taskKey+":id",orderId,jedis);
                UtilsComet.publish(UtilsComet.service.getId()+"-order-create",exchangeTransaction,jedis);

                MonitorRequest monitorRequest = new MonitorRequest(transaction_id, orderId,exchangeOrder);
                UtilsComet.addToQueue(UtilsComet.service.getOrderMonitor(),UtilsComet.convertToString(monitorRequest),jedis);
                System.out.println("Order placed successfully");

            }else{
                System.out.println("Order Failed");
                ExchangeTransaction exchangeTransaction = new ExchangeTransaction(0L,Long.parseLong(exchangeOrder.getId()),UtilsComet.service.getId(),"",exchangeOrder.getPrice(),exchangeOrder.getQuantity(),"Failed");
                UtilsComet.publish(UtilsComet.service.getId()+"-order-failed",exchangeTransaction,jedis);
            }



        }
    }
    public boolean isSuccess(String response){
        return  response!=null && response.length() < 50;
    }
}
