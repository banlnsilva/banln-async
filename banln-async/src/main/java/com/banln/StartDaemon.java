package com.banln;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartDaemon implements InitializingBean {

	private final CoffeeService coffeeService;
	
	@Override
	public void afterPropertiesSet() {
		boolean isLoop = true;
		int ii = 0;
		String coffeeName = "";
		
		try {
			while(isLoop) {
				if((ii%3) == 1)	coffeeName = "latte";
				else if((ii%3) == 2) coffeeName = "mocha";
				else coffeeName = "americano";
				// int aaa = coffeeComponent.getPriceAsync("latte").join();
				
		        CompletableFuture<Integer> future = coffeeService.getPriceAsyncWithCompletableFuture02("latte");
		        log.info("non blocking 1 : ...");
		        future.thenAccept(p -> log.info("latte's price is : " + p));
		        log.info("non blocking 2 : ...");
				
				log.info("111111111111111111111111>>>" + ii);
				Thread.sleep(10);
				ii++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
