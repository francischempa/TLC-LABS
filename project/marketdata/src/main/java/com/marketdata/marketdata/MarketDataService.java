package com.marketdata.marketdata;

import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.List;

@RestController
public class MarketDataService {
    Jedis jedis = new Jedis();
    @PostMapping("/comet/md/update/{exchange}")
    public void update(@PathVariable String exchange, @RequestBody String marketdata){
        UtilsComet.publish("marketdata:"+exchange,marketdata,jedis);
        System.out.println(marketdata);
    }
}
