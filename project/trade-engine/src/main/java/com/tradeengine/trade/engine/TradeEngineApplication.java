package com.tradeengine.trade.engine;

import com.tradeengine.trade.engine.taskqueues.MakeOrderQueue;
import com.tradeengine.trade.engine.taskqueues.OrderMonitorQueue;
import com.tradeengine.trade.engine.trademodels.Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class TradeEngineApplication {

	public static void main(String[] args) {

		SpringApplication.run(TradeEngineApplication.class, args);
		ExecutorService queuesExecutorService = Executors.newFixedThreadPool(Config.getNumberOfTaskQueues());

		queuesExecutorService.execute(new MakeOrderQueue());
		queuesExecutorService.execute(new OrderMonitorQueue());
//		queuesExecutorService.execute(new CancelOrderQueue());


//		Order order = UtilsComet.convertToObject(data,Order.class);
	}

}
