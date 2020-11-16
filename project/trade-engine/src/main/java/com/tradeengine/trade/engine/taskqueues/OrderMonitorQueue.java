package com.tradeengine.trade.engine.taskqueues;

import com.tradeengine.trade.engine.Config;
import com.tradeengine.trade.engine.UtilsComet;
import com.tradeengine.trade.engine.exchangeconnectivity.ExchConnectivity;
import com.tradeengine.trade.engine.trademodels.ExchangeOrder;
import com.tradeengine.trade.engine.trademodels.ValidatedOrder;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderMonitorQueue implements Runnable{
    private Jedis jedis;

    public OrderMonitorQueue() {
        this.jedis = new Jedis(Config.getRedisAddress());
    }
    @Override
    public void run() {
        while (true){
            String orderHash = UtilsComet.getFromQueue(Config.getOrderMonitorQueue(),jedis);
            if(orderHash == null) continue;
            long numberOfResults = UtilsComet.getQueueLen(orderHash + "orderbook",jedis);
            if(numberOfResults == Config.numberOfExchanges){ // IS RESPONSE EQUAL THE NUMBER OF EXCHANGES
                String data = UtilsComet.getCacheValue(orderHash+"monitor",jedis);
                ValidatedOrder validatedOrder = UtilsComet.convertToObject(data,ValidatedOrder.class);
                UtilsComet.deleteData(orderHash+"monitor",jedis);

                List<ExchangeOrder> exchangeOrderList = new ArrayList<>();
                for (int index = 0;index<Config.numberOfExchanges;index++){
                    data = UtilsComet.getFromQueue(orderHash + "orderbook",jedis);
                    ExchangeOrder[] exchangeOrders = UtilsComet.convertToObject(data,ExchangeOrder[].class);
                    exchangeOrderList.addAll(Arrays.asList(exchangeOrders));
                }
                UtilsComet.deleteData(orderHash + "orderbook",jedis);

                System.out.println("#111");
                System.out.println(exchangeOrderList);

                final double orderPrice = validatedOrder.getPrice();
                switch (validatedOrder.getSide().toUpperCase()){
                    case "BUY":{
                        exchangeOrderList = exchangeOrderList.stream().sorted((o1, o2) -> Double.compare(o1.getPrice(),o2.getPrice())).filter(exchangeOrder -> exchangeOrder.getPrice() >= orderPrice).peek(exchangeOrder -> exchangeOrder.setSide("BUY")).collect(Collectors.toList());
                    }break;
                    case "SELL":{
                        exchangeOrderList = exchangeOrderList.stream().sorted((o1, o2) -> Double.compare(o2.getPrice(),o1.getPrice())).filter(exchangeOrder -> exchangeOrder.getPrice() >= orderPrice).peek(exchangeOrder -> exchangeOrder.setSide("SELL")).collect(Collectors.toList());
                    }break;
                    default:{}
                }

                System.out.println("#222");
                System.out.println(exchangeOrderList);

                if(exchangeOrderList.size() == 0){ // NO DEAL AVAILABLE ON ALL EXCHANGES
                    UtilsComet.addToQueue(Config.getMakeOrderQueueFromOvToTe(),UtilsComet.convertToString(validatedOrder),jedis); // add as a new order from order validator
                    continue;
                }

//                #fixme, Enhance using streams
                List<ExchangeOrder> selectedOrders = new ArrayList<>();
                int qtyNeeded = validatedOrder.getQuantity();
                for (ExchangeOrder exchangeOrder : exchangeOrderList) {
                    int availableQty = exchangeOrder.getQuantity() - exchangeOrder.getCumulativeQuantity();
                    if (availableQty >= qtyNeeded) {
                        exchangeOrder.setQuantity(qtyNeeded);
                        selectedOrders.add(exchangeOrder);
                        break;
                    }
                    exchangeOrder.setQuantity(availableQty);
                    selectedOrders.add(exchangeOrder);
                    qtyNeeded -= availableQty;
                }
//                #fixme, qtyNeeded could be positive (currently ignored)

                System.out.println("#333");
                System.out.println(selectedOrders);

                for (ExchConnectivity exchConnectivity : Config.exchConnectivityList) {
                    List<ExchangeOrder> exchangeSpecificOrders = selectedOrders.stream().filter(exchangeOrder -> exchangeOrder.getExchange().equals(exchConnectivity.getId())).collect(Collectors.toList());
                    List<Double> priceList = exchangeOrderList.stream().map(ExchangeOrder::getPrice).distinct().collect(Collectors.toList());
                    priceList.forEach(
                            price -> {
                                List<ExchangeOrder> exchangeOrders = exchangeSpecificOrders.stream().filter(exchangeOrder -> exchangeOrder.getPrice()==price).collect(Collectors.toList());
                                Optional<Integer> optionalQuantity = exchangeOrders.stream().map(ExchangeOrder::getQuantity).reduce(Integer::sum);
                                if(optionalQuantity.isPresent()){
                                    ExchangeOrder eo = exchangeOrders.get(0);
                                    int quantity = optionalQuantity.get();
                                    ExchangeOrder exchangeOrder = new ExchangeOrder(eo.getProduct(),quantity,eo.getPrice(),eo.getSide(),0);
                                    exchangeOrder.setExchange(exchConnectivity.getId());
                                    UtilsComet.addToQueue(orderHash+ ":" +exchConnectivity.getId()+":makeorder",UtilsComet.convertToString(exchangeOrder),jedis);
                                    UtilsComet.addToQueue(exchConnectivity.getMakeOrderQueueKey(),orderHash + ":" + exchConnectivity.getId()+":makeorder",jedis);
                                }
                            }
                    );
                    ExchangeOrder exchangeOrder = new ExchangeOrder(validatedOrder.getProduct(),0,0,validatedOrder.getSide(),0);
                    exchangeOrder.setExchange(exchConnectivity.getId());
                    UtilsComet.addToQueue(orderHash+ ":" +exchConnectivity.getId()+":makeorder",UtilsComet.convertToString(exchangeOrder),jedis);
                    UtilsComet.addToQueue(exchConnectivity.getMakeOrderQueueKey(),orderHash + ":" + exchConnectivity.getId()+":makeorder",jedis);

                }
//                while (true){}

            }else{//ALL RESULTS NOT READY; PUT TASK BACK INTO QUEUE
                UtilsComet.addToQueue(Config.getOrderMonitorQueue(),orderHash,jedis);
            }
        }
    }
}
