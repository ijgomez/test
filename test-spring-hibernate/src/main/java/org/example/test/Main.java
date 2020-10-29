package org.example.test;

import java.util.List;

import org.example.test.core.domain.Person;
import org.example.test.core.service.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

	public static void main(String[] args) {
		(new Main()).run(args);
	}

	private static final String[] PARAMETERS_VALUES = {"mysql", "hsqldb"}; 
	
	private ApplicationContext applicationContext;
	
	//mysql
	public void run(String[] args) {
		
		if (args != null && args.length == 1 && (args[0].equals(PARAMETERS_VALUES[0]) || args[0].equals(PARAMETERS_VALUES[1]))) {
			
			System.setProperty("spring.profiles.active", args[0]);
			applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
			
			log.info("--------------------------------------------------------------");
			
			PersonService personService = applicationContext.getBean(PersonService.class);
			
			Person peter = new Person("Peter", "Sagan");
	        Person nasta = new Person("Nasta", "Kuzminova");
	         
	        personService.insert(peter);
	        personService.insert(nasta);
	         
	        List<Person> persons = personService.list();
	        for (Person person : persons) {
	        	log.debug("Value: {}", person);
	        }
			
			
	        log.info("--------------------------------------------------------------");
			((ClassPathXmlApplicationContext)applicationContext).close();
		} else {
			System.err.println("Falta parametro: " + PARAMETERS_VALUES[0] + " o " + PARAMETERS_VALUES[1]);
		}
		
		
		
	}
}
