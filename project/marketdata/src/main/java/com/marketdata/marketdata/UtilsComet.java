package com.marketdata.marketdata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

public class UtilsComet {
//    public static final Jedis jedis = new Jedis(Config.getRedisAddress());
    public static  <T> T  convertToObject(String data, Class<T> type){
        ObjectMapper objectMapper = new ObjectMapper();
        T t = null;
        try {
            t = objectMapper.readValue(data, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
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

    public  static String getFromQueue(String queueName,Jedis jedis){
        return jedis.rpop(queueName);
    }

    public static long getQueueLen(String key,Jedis jedis){
        return jedis.llen(key);
    }

    public static void addToQueue(String queueName, String data,Jedis jedis){
        jedis.lpush(queueName, data);
    }

    public static void publish(String channel,String message,Jedis jedis){
        jedis.publish(channel,message);
    }
}
