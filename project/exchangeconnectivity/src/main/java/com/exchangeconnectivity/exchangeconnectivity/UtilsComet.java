package com.exchangeconnectivity.exchangeconnectivity;

import com.exchangeconnectivity.exchangeconnectivity.exchangemodels.Blockable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.function.client.WebClient;
import redis.clients.jedis.Jedis;

public class UtilsComet {
    public static ExchConnectivity service = new ExchConnectivity("exch1");
    public static int numberOfTaskQueues = 4;
    public static String redisAddress = "localhost";
    public static String orderBookBasesUrl = "https://exchange.matraining.com";
    public static String API_KEY = "2e3a035d-f570-4472-8e17-90ddcb1c498e";

    public static final WebClient exchangeWebClient = WebClient.builder().baseUrl(UtilsComet.orderBookBasesUrl).defaultHeader("Content-Type", "application/json").build();
//    public static final Jedis jedis = new Jedis(Config.getRedisAddress());
    public static  <T> T  convertToObject(String data, Class<T> type){
        ObjectMapper objectMapper = new ObjectMapper();
        T t = null;
        try {
            t = objectMapper.readValue(data, type);
        } catch (JsonProcessingException ignore) {}
        return t;
    }
    public static <T> String convertToString(T t){
        ObjectMapper objectMapper = new ObjectMapper();
        String str = null;
        try{
            str = objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException ignore) {}
        return str;
    }

    public static String getCacheValue(String key,Jedis jedis){
        return jedis.get(key);
    }

    public static void setCacheValue(String key, String value,Jedis jedis){
        jedis.set(key, value);
    }

    public static void deleteData(String key,Jedis jedis){
        jedis.del(key);
    }
    public static boolean isOrderTypeInQueue(Blockable order,Jedis jedis){
        return !(getCacheValue(order.getOrderType(),jedis) == null);
    }

    public static void putOrderTypeInQueue(Blockable order,Jedis jedis){
        setCacheValue(order.getOrderType(),convertToString(order),jedis);
    }

//    public static boolean isOrderTypeInQueue(ValidatedOrder validatedOrder){
//        return !(getCacheValue(validatedOrder.getOrderType()) == null);
//    }

    public static long getQueueLen(String key,Jedis jedis){
        return jedis.llen(key);
    }

    public  static String getFromQueue(String queueName,Jedis jedis){
        return jedis.rpop(queueName);
    }

    public static void addToQueue(String queueName, String data,Jedis jedis){
        jedis.lpush(queueName, data);
    }
}
