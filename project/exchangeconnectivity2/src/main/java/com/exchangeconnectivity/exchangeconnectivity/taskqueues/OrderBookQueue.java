package com.exchangeconnectivity.exchangeconnectivity.taskqueues;

import com.exchangeconnectivity.exchangeconnectivity.Config;
import com.exchangeconnectivity.exchangeconnectivity.UtilsComet;
import com.exchangeconnectivity.exchangeconnectivity.responses.OrderBookTaskResponse;
import com.exchangeconnectivity.exchangeconnectivity.tasks.OrderBookTask;
import com.exchangeconnectivity.exchangeconnectivity.exchangemodels.ExchangeOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.function.client.WebClient;
import redis.clients.jedis.Jedis;

import java.util.*;

public class OrderBookQueue implements Runnable {
    private final Jedis jedis;

    public OrderBookQueue() {
        this.jedis = new Jedis(Config.getRedisAddress());
    }
    @Override
    public void run() {
        while (true){
            String taskKey = UtilsComet.getFromQueue(Config.service.getOrderBookQueueKey(),jedis);
            if(taskKey == null) continue;
            String rawOrderBookTask = UtilsComet.getCacheValue(taskKey,jedis);
            UtilsComet.deleteData(taskKey,jedis);

            if(rawOrderBookTask==null){
//                ORDER POSSIBLY DELETED WHILES IN QUEUE
                continue;
            }
            OrderBookTask orderBookTask = UtilsComet.convertToObject(rawOrderBookTask,OrderBookTask.class);
            ExchangeOrder[] exchangeOrders = UtilsComet.exchangeWebClient.get().uri(orderBookTask.generatePath().toLowerCase()).retrieve().bodyToMono(ExchangeOrder[].class).block();
//            MOCKING WEB REQUEST TO EXCHANGE
//            String side = "SELL";
//            if(orderBookTask.getSide().toUpperCase().equals("SELL")){
//                side = "BUY";
//            }
////            ExchangeOrder[] exchangeOrders =new ExchangeOrder[]{new ExchangeOrder(orderBookTask.getProduct(),50,1.5,side,5)};



            if(exchangeOrders == null) exchangeOrders = new ExchangeOrder[]{};
            for (ExchangeOrder exchangeOrder : exchangeOrders) {
                exchangeOrder.setExchange(Config.service.getId());
            }
            String data = UtilsComet.convertToString(exchangeOrders);
            UtilsComet.addToQueue(orderBookTask.getId()+"orderbook",data,jedis);
//            while (true){}
        }
    }
}
