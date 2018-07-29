package org.example.test.core.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.example.test.core.helper.TestObjectFactory;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class PersonDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private TestObjectFactory objectFactory;

	@Test
	public void testInsert() {
		
		Person register = this.objectFactory.buildPerson();
        
        personDao.insert(register);
        
        assertNotNull(register.getId());
        
        List<Person> results = personDao.list();
        
        assertEquals(1, results.size());
        
	}

	@Test
	public void testUpdate() {
		
		String surnameModif = RandomStringUtils.randomAlphanumeric(20);
		
		Person register = this.objectFactory.buildPerson();
        personDao.insert(register);

        register.setSurname(surnameModif);
        personDao.update(register);
        
        String surnameResult = personDao.load(register.getId()).getSurname();
        
		assertEquals(surnameModif, surnameResult);
        
	}

	@Test
	public void testRemove() {
		Person register = this.objectFactory.buildPerson();
        
        personDao.insert(register);
        
        personDao.remove(register);
        
        List<Person> results = personDao.list();
        
        assertEquals(0, results.size());
	}

	@Test
	public void testLoad() {
		Person register = this.objectFactory.buildPerson();
         
        Long id = personDao.insert(register);
        
        Person result = personDao.load(id);

        assertEquals(register, result);
	}

	@Test
	public void testFind() {
        
        personDao.insert(this.objectFactory.buildPerson());
        personDao.insert(this.objectFactory.buildPerson());
         
        List<Person> results = personDao.list();
        
        assertEquals(2, results.size());
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testRestrictionColumnNameUnique() {
		String nameUnique = RandomStringUtils.randomAlphanumeric(20);

        personDao.insert(new Person(nameUnique, RandomStringUtils.randomAlphanumeric(20)));
        personDao.insert(new Person(nameUnique, RandomStringUtils.randomAlphanumeric(20)));
                
	}
	
	@Test(expected = PropertyValueException.class)
	public void testRestrictionColumnNameNotNullInsert() {

        personDao.insert(new Person(null, RandomStringUtils.randomAlphanumeric(20)));
       
	}
	
	@Test(expected = PropertyValueException.class)
	public void testRestrictionColumnNameNotNullUpdate() {
		
		Person entity = this.objectFactory.buildPerson();

        personDao.insert(entity);
       
        assertNotNull(entity.getId());
        
        entity.setName(null);
        
        personDao.update(entity);
	}

}
