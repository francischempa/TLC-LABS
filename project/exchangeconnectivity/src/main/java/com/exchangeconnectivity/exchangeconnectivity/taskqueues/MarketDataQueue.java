package com.exchangeconnectivity.exchangeconnectivity.taskqueues;

import com.exchangeconnectivity.exchangeconnectivity.UtilsComet;
import redis.clients.jedis.Jedis;

public class MarketDataQueue implements Runnable {
    private final Jedis jedis;

    public MarketDataQueue() {
        this.jedis = new Jedis(UtilsComet.redisAddress);
    }

    @Override
    public void run() {
        while (true) {
            String taskKey = UtilsComet.getFromQueue(UtilsComet.service.getMakeOrderQueueKey(), jedis);
        }
    }
}
