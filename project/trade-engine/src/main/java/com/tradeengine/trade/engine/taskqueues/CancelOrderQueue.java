package com.tradeengine.trade.engine.taskqueues;
import com.tradeengine.trade.engine.Config;
import redis.clients.jedis.Jedis;

public class CancelOrderQueue implements Runnable{
    private Jedis jedis;
    public CancelOrderQueue() {
        this.jedis = new Jedis(Config.getRedisAddress());
    }
    @Override
    public void run() {
        while (true){

        }
    }
}
