package org.example.test.core.service;

import java.util.List;

import org.example.test.core.domain.Person;
import org.example.test.core.domain.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonService {
	
	@Autowired
	private PersonDao personDao;

	public void save(Person person) {
		personDao.insert(person);
	}

	@Transactional(readOnly = true)
	public List<Person> list() {
		return personDao.findAll();
	}
	
	@Transactional(readOnly = true)
	public Long count() {
		return personDao.countAll();
	}

}
