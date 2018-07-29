package org.example.test;

import java.util.List;

import org.example.test.core.domain.Person;
import org.example.test.core.service.PersonService;
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
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml", "applicationContext-datasource.xml");
		LOGGER.info("--------------------------------------------------------------");
		
		PersonService personService = applicationContext.getBean(PersonService.class);
		
		Person peter = new Person("Peter", "Sagan");
        Person nasta = new Person("Nasta", "Kuzminova");
         
        personService.save(peter);
        personService.save(nasta);
         
        List<Person> persons = personService.list();
        for (Person person : persons) {
        	LOGGER.debug("Person: {}", person);
        }
        
        LOGGER.debug("Total: {}", personService.count());
		
		
        LOGGER.info("--------------------------------------------------------------");
		((ClassPathXmlApplicationContext)applicationContext).close();
	}
}
