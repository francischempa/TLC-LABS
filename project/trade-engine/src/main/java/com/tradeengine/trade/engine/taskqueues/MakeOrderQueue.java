package com.tradeengine.trade.engine.taskqueues;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradeengine.trade.engine.Config;
import com.tradeengine.trade.engine.UtilsComet;
import com.tradeengine.trade.engine.exchangeconnectivity.ExchConnectivity;
import com.tradeengine.trade.engine.tasks.OrderBookTask;
import com.tradeengine.trade.engine.trademodels.ValidatedOrder;
import redis.clients.jedis.Jedis;

public class MakeOrderQueue implements Runnable {
    private Jedis jedis;

    public MakeOrderQueue() {
        this.jedis = new Jedis(Config.getRedisAddress());
    }

    @Override
    public void run() {
//        ValidatedOrder validatedOrder1 = new ValidatedOrder("order1","IBM",100,1.5,"BUY");
//        String data = "{\"id\":\"order1\",\"product\":\"nflx\",\"quantity\":1000,\"price\":1,\"side\":\"BUY\"}";
//        UtilsComet.setCacheValue("order1",data);
//        UtilsComet.addToQueue(Config.getMakeOrderQueueFromOvToTe(),"order1");

//        data = "{\"id\":\"order2\",\"product\":\"IBM\",\"quantity\":100,\"price\":1.5,\"side\":\"SELL\"}";
//        UtilsComet.setCacheValue("order2",data);
//        UtilsComet.addToQueue(Config.getMakeOrderQueueFromOvToTe(),"order2");
//
//        data = "{\"id\":\"order3\",\"product\":\"GOOG\",\"quantity\":100,\"price\":1.5,\"side\":\"BUY\"}";
//        UtilsComet.setCacheValue("order3",data);
//        UtilsComet.addToQueue(Config.getMakeOrderQueueFromOvToTe(),"order3");


        String rawValidatedOrderTask;

        while (true){

            String taskKey = UtilsComet.getFromQueue(Config.getMakeOrderQueueFromOvToTe(),jedis);
            if(taskKey == null) continue;
            rawValidatedOrderTask = UtilsComet.getCacheValue(taskKey,jedis);
            UtilsComet.deleteData(taskKey,jedis);

            if(rawValidatedOrderTask==null) {
//                ORDER POSSIBLY DELETED WHILES IN QUEUE
                continue;
            };
            ValidatedOrder validatedOrder = UtilsComet.convertToObject(rawValidatedOrderTask,ValidatedOrder.class);
            String orderBookTaskString = UtilsComet.convertToString(new OrderBookTask(validatedOrder.getId(),validatedOrder.getProduct(),validatedOrder.getSide()));

            String orderTypeStatus = UtilsComet.getCacheValue(validatedOrder.getOrderType(),jedis);
            if( !UtilsComet.isOrderTypeInQueue(validatedOrder,jedis) ) {
                UtilsComet.putOrderTypeInQueue(validatedOrder,jedis);

                UtilsComet.setCacheValue(validatedOrder.getId()+"monitor",rawValidatedOrderTask,jedis);
                UtilsComet.addToQueue(Config.getOrderMonitorQueue(),validatedOrder.getId(),jedis);

                for (ExchConnectivity exchConnectivity : Config.exchConnectivityList) {
                    if (true) { // Exchanges Allowed to be used by admins
                        UtilsComet.setCacheValue(validatedOrder.getId()+exchConnectivity.getId(), orderBookTaskString,jedis);
                        UtilsComet.addToQueue(exchConnectivity.getOrderBookQueueKey(), validatedOrder.getId()+exchConnectivity.getId(),jedis);
                    }
                }
            }else{
                UtilsComet.setCacheValue(taskKey,rawValidatedOrderTask,jedis);
                UtilsComet.addToQueue(Config.getMakeOrderQueueFromOvToTe(),taskKey,jedis);
            }
//            while (true){}
        }
    }
}
