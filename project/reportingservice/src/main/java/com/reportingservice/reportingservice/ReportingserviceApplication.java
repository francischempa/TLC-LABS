package com.reportingservice.reportingservice;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import redis.clients.jedis.Jedis;

@SpringBootApplication
public class ReportingserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportingserviceApplication.class, args);
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ReportingServiceConfig.class);
		ctx.refresh();
		SocketIOServer server = ctx.getBean(SocketIOServer.class);
		server.addDisconnectListener(new DisconnectListener() {
			@Override
			public void onDisconnect(SocketIOClient client) {
				System.err.printf("client disconnected: [%s]\n",client.getRemoteAddress());
			}
		});
		server.addConnectListener(new ConnectListener() {
			@Override
			public void onConnect(SocketIOClient client) {
				System.out.printf("client connected: [%s]\n",client.getRemoteAddress());
			}
		});

		server.start();
		new  Subscriber(ctx).register();
	}

}
