package com.reportingservice.reportingservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.function.client.WebClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class UtilsComet {
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

    public static long getQueueLen(String key,Jedis jedis){
        return jedis.llen(key);
    }

    public  static String getFromQueue(String queueName,Jedis jedis){
        return jedis.rpop(queueName);
    }

    public static void addToQueue(String queueName, String data,Jedis jedis){
        jedis.lpush(queueName, data);
    }

    public static void subscribe(JedisPubSub jedisPubSub,Jedis jedis,  String... channels){
        jedis.subscribe(jedisPubSub,channels);
    }
}
