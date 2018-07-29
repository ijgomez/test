package org.example.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		(new Main()).run(args);
	}
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	
	private ApplicationContext applicationContext;
	
	
	public void run(String[] args) {
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		LOGGER.info("--------------------------------------------------------------");
		
//		SignalService signalService = applicationContext.getBean(SignalService.class);
//		
//		Signal signal1 = new Signal();
//        Signal signal2 = new Signal();
//         
//        signalService.insert(signal1);
//        signalService.insert(signal2);
//         
//        List<Signal> signals = signalService.findByCriteria(null);
//        for (Signal signal : signals) {
//        	LOGGER.debug("Signal: {}", signal);
//        }
		
		
        LOGGER.info("--------------------------------------------------------------");
		((ClassPathXmlApplicationContext)applicationContext).close();
	}
}
