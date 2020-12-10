package com.exchangeconnectivity.exchangeconnectivity.subscribers;

import com.exchangeconnectivity.exchangeconnectivity.UtilsComet;
import com.exchangeconnectivity.exchangeconnectivity.exchangemodels.ExchangeOrder;
import com.exchangeconnectivity.exchangeconnectivity.tasks.OrderRequest;
import org.springframework.web.reactive.function.client.WebClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.Arrays;

public class Subscriber implements Runnable {
    private final Jedis jedis;
    private final WebClient exchangeWebClient = WebClient.builder().baseUrl(UtilsComet.orderBookBasesUrl).defaultHeader("Content-Type", "application/json").build();

    public Subscriber() {
        this.jedis = new Jedis(UtilsComet.redisAddress);
    }


    @Override
    public void run() {
        JedisPubSub jedisPubSub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("Message "+ message);
                MarketData[] marketDataList = UtilsComet.convertToObject(message,MarketData[].class);
////                OPERATE ON DATA HERE
//
////                done
                if (("marketdata:"+UtilsComet.service.getId()).equals(channel)) {
                    Arrays.stream(marketDataList).forEach(marketData -> {
                        Jedis publisherjedis = new Jedis(UtilsComet.redisAddress);
                        UtilsComet.setCacheValue("md:" + marketData.getTICKER(), UtilsComet.convertToString(marketData), publisherjedis);
                        publisherjedis.close();
                    });
                }else if (("cancelorder:"+UtilsComet.service.getId()).equals(channel)){
                    cancelOrder(UtilsComet.convertToObject(message, OrderRequest.class));
                }

            }
        };
        UtilsComet.subscribe(jedisPubSub,jedis,
                "marketdata:"+UtilsComet.service.getId(),
                "cancelorder:"+UtilsComet.service.getId());
    }

    public void cancelOrder(OrderRequest orderRequest){
        String response = exchangeWebClient.delete().uri("/"+UtilsComet.API_KEY+"/order/"+ orderRequest.getOrderid())
                .retrieve()
                .bodyToMono(String.class).block();
        if(response.equals("true")){
            UtilsComet.publish(UtilsComet.service.getId()+"-order-cancel",orderRequest,jedis);
        }
    }
}
























