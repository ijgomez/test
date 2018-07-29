package org.example.test.core.domain.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class HibernateDaoBase<E, K extends Serializable> {

	private static final Logger LOGGER = LoggerFactory.getLogger(HibernateDaoBase.class);

	private SessionFactory sessionFactory;

	protected Class<? extends E> entityClass;

	@SuppressWarnings("unchecked")
	public HibernateDaoBase() {
		entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
	
	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session currentSession() {
        return this.sessionFactory.getCurrentSession();
    }
    
    protected Session openSession() {
    	return this.sessionFactory.openSession();
    }

    @SuppressWarnings("unchecked")
	public K insert(E entity) {
    	return (K) this.currentSession().save(entity);
    }
    

    public void insert(List<E> entities) {
    	Session session;

    	session = this.currentSession();
    	
    	//LOGGER.debug("Transaction Active: " + session.getTransaction().isActive());
    	
    	int count = 0;
    	for (E entity : entities) {
			session.save(entity);
			count++;
			
			if ( count % 1000 == 0 ) { //2000, same as the JDBC batch size flush a batch of inserts and release memory:
				LOGGER.trace("flush a batch of inserts (1000) and release memory.");
		        session.flush();
		        session.clear();
		    }
		}

//		LOGGER.debug("flush a batch of inserts and release memory - final");
    	session.flush();
        session.clear();

    }

    public void update(E entity) {
    	Session session = this.currentSession();
		session.saveOrUpdate(entity);
		session.flush();
    }

    public void remove(E entity) {
    	Session session = this.currentSession();
		session.delete(entity);
		session.flush();
    }
    
    @SuppressWarnings("unchecked")
	public E load(K key) {
        return (E) currentSession().get(entityClass, key);
    }
    
    @SuppressWarnings("unchecked")
	public List<E> list() {
        return this.currentSession().createCriteria(entityClass).list();
    }

    @SuppressWarnings("unchecked")
	public List<E> find(Map<String, Object> parameters) {
    	Criteria criteria = this.currentSession().createCriteria(entityClass);
    	
    	Set<String> fieldNames = parameters.keySet();
    	
    	for (String fieldname : fieldNames) {
			criteria.add(Restrictions.ilike(fieldname, parameters.get(fieldname)));
		}
    	return criteria.list();
    }

	@SuppressWarnings("unchecked")
	public List<E> findByCriteria(List<Criterion> criterions, Order order) {
    	Criteria criteria = this.currentSession().createCriteria(entityClass);
    	
    	if (criterions != null && !criterions.isEmpty()) {
	    	for (Criterion criterion : criterions) {
	    		criteria.add(criterion);
	    	}
    	}
    	
    	if (order != null) {
    		criteria.addOrder(order);
    	}
		
    	return criteria.list();
    }
	
	public void flush() {
		this.currentSession().flush();
	}
}
