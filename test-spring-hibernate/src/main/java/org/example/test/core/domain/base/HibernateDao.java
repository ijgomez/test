package org.example.test.core.domain.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public interface HibernateDao<E, K extends Serializable> {

	void setSessionFactory(SessionFactory sessionFactory);
	
	K insert(E entity);

	void update(E entity);

	void remove(E entity);

	E load(K key);

	List<E> list();
	
	List<E> find(Map<String, Object> parameters);
	
	List<E> findByCriteria(List<Criterion> criterions, Order order);
	
	void flush();
}
