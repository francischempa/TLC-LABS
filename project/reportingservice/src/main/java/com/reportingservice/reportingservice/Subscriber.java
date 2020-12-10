package com.reportingservice.reportingservice;


import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Subscriber {
    private final Jedis jedis;
    private final SocketIOServer server;
    private final List<String> channels = new ArrayList<>();
    public Subscriber(AnnotationConfigApplicationContext ctx){
        this.server = ctx.getBean(SocketIOServer.class);
        this.jedis = ctx.getBean(Jedis.class);
        channels.add("tradeengine-transaction");

        channels.add("exch1-order-create");  // ORDER PLACED
        channels.add("exch1-order-update");  // ORDER EXECUTED
        channels.add("exch1-order-cancel");  // USER CANCELLED ORDER
        channels.add("exch1-order-failed");  // FAILED TO PLACE ORDER



        channels.add("ordervalidator-validation-success");
        channels.add("ordervalidator-validation-failed");
        channels.add("clientconnectivity");
        channels.add("marketdata");
    }

    public void subscribeToChannels(JedisPubSub jedisPubSub ){
        jedis.subscribe(jedisPubSub,channels.toArray(String[]::new));
    }

    public void addChannel(String channel){
        channels.add(channel);
    }

    public void register(){
        JedisPubSub payload = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.printf(" (%s) -- From: %s - %s\n",LocalDateTime.now().toString(),channel,message);
                server.getBroadcastOperations().sendEvent("chatevent", message);
            }
        };
        subscribeToChannels(payload);
    }
}
