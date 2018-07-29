package org.example.test.core.helper;

import org.apache.commons.lang.RandomStringUtils;
import org.example.test.core.domain.Person;
import org.springframework.stereotype.Component;

@Component
public class TestObjectFactory {

	public Person buildPerson() {
		Person person;
		
		person = new Person();
		person.setName(RandomStringUtils.randomAlphanumeric(10));
		person.setSurname(RandomStringUtils.randomAlphanumeric(20));
		
		return person;
	}

}
