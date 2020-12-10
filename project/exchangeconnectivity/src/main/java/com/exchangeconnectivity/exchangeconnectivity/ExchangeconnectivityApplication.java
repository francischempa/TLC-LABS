package com.exchangeconnectivity.exchangeconnectivity;

import com.exchangeconnectivity.exchangeconnectivity.subscribers.Subscriber;
import com.exchangeconnectivity.exchangeconnectivity.taskqueues.MakeOrderQueue;
import com.exchangeconnectivity.exchangeconnectivity.taskqueues.OrderBookQueue;
import com.exchangeconnectivity.exchangeconnectivity.taskqueues.OrderMonitorQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class ExchangeconnectivityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeconnectivityApplication.class, args);
		ExecutorService queuesExecutorService = Executors.newFixedThreadPool(UtilsComet.numberOfTaskQueues);

		queuesExecutorService.execute(new MakeOrderQueue());
		queuesExecutorService.execute(new OrderBookQueue());
		queuesExecutorService.execute(new OrderMonitorQueue());
		queuesExecutorService.execute(new Subscriber());

	}

}