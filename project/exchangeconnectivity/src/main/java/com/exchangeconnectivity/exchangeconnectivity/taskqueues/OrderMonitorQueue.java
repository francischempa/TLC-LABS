package com.exchangeconnectivity.exchangeconnectivity.taskqueues;

import com.exchangeconnectivity.exchangeconnectivity.UtilsComet;
import com.exchangeconnectivity.exchangeconnectivity.exchangemodels.ExchangeOrder;
import com.exchangeconnectivity.exchangeconnectivity.tasks.MonitorRequest;
import redis.clients.jedis.Jedis;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderMonitorQueue implements Runnable{
    private final Jedis jedis;

    public OrderMonitorQueue() {
        this.jedis = new Jedis(UtilsComet.redisAddress);
    }
    @Override
    public void run() {
        while(true) {
            String data = UtilsComet.getFromQueue(UtilsComet.service.getOrderMonitor(), jedis);
            if (data == null) continue;
            System.out.println(data);
            final MonitorRequest monitorRequest = UtilsComet.convertToObject(data, MonitorRequest.class);
            if (monitorRequest == null) continue;
            System.out.println("MONITOR REQUEST");
            AtomicInteger statusCode = new AtomicInteger(0);
            String responseBody = UtilsComet.exchangeWebClient.get().uri("/" + UtilsComet.API_KEY + "/order/" + monitorRequest.getOrder_key())
                    .exchange()
                    .flatMap(response -> {
                        String responseValue = "";
                        if (response.statusCode().is5xxServerError()) {
                            statusCode.set(500);
                        } else {
                            statusCode.set(200);
                        }
                        return response.bodyToMono(String.class);
                    }).block();
            System.out.println(responseBody);
            if(statusCode.get() == 200){
                ExchangeOrder order = UtilsComet.convertToObject(responseBody,ExchangeOrder.class);
                if(order.getCumulativeQuantity() != monitorRequest.exchangeOrder.getCumulativeQuantity()){
                    System.out.println("MONITOR: ADDED TO QUEUE - TRANSACTED");
                    monitorRequest.exchangeOrder.setCumulativeQuantity(order.getCumulativeQuantity());
                    UtilsComet.addToQueue(UtilsComet.service.getOrderMonitor(), UtilsComet.convertToString(monitorRequest), jedis);
                    UtilsComet.publish(UtilsComet.service.getId()+"-order-update",monitorRequest,jedis);
                }else{
                    System.out.println("MONITOR: ADDED TO QUEUE - NO TRANSACTION");
                    UtilsComet.addToQueue(UtilsComet.service.getOrderMonitor(), UtilsComet.convertToString(monitorRequest), jedis);
                }
            }else if(statusCode.get() == 500){
                System.out.println("MONITOR: ALL TRANSACTED");
                monitorRequest.exchangeOrder.setCumulativeQuantity(monitorRequest.exchangeOrder.getQuantity());
                UtilsComet.publish(UtilsComet.service.getId()+"-order-update",monitorRequest,jedis);
            }
        }
    }

}
