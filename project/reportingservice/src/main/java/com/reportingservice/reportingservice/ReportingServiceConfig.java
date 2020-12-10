package com.reportingservice.reportingservice;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.corundumstudio.socketio.Configuration;
import redis.clients.jedis.Jedis;

@org.springframework.context.annotation.Configuration
public class ReportingServiceConfig {

    @Autowired
    SocketIOServer server;

    @Autowired
    Jedis jedis;

    @Bean
    public Jedis redisClient(){
        return new Jedis("localhost");
    }

    @Bean
    public SocketIOServer socketIOServer(){
        Configuration configuration = new Configuration();
        configuration.setHostname("localhost");
        configuration.setPort(9092);
        SocketConfig socketConfig = configuration.getSocketConfig();
        socketConfig.setReuseAddress(true);
        return new SocketIOServer(configuration);
    }


}
