package org.example.test.model;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDao {
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<Contact> getContacts(int start, int limit) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Contact.class);

		
		return (List<Contact>) hibernateTemplate.findByCriteria(criteria, start, limit);
	}

	public void deleteContact(int id){
		Object record = hibernateTemplate.load(Contact.class, id);
		hibernateTemplate.delete(record);
	}

	public Contact saveContact(Contact contact){
		hibernateTemplate.saveOrUpdate(contact);
		return contact;
	}

	public int getTotalContacts(){
		return DataAccessUtils.intResult(hibernateTemplate.find("SELECT COUNT(*) FROM Contact"));
	}

}
