package com.demo.slk.application.airtel_wynk;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class OnApplicationStart {
	public static int counter;
	
	public void onApplicationEvent(ContextRefreshedEvent event) {
		counter++;
	}
	
}
