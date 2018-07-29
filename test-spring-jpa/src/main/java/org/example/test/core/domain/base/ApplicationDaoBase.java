package org.example.test.core.domain.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

//@Repository
//@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public abstract class ApplicationDaoBase<E, K extends Serializable> implements ApplicationDao<E, K> {

	protected Class<E> entityClass;

	@PersistenceContext
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public ApplicationDaoBase() {
		this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public E insert(E e) {
		this.entityManager.persist(e);
		return e;
	}

	
	@Override
	public E update(E e) {
		e = this.entityManager.merge(e);
		this.flush();
		return e;
	}

	@Override
	public void delete(E e) {
		this.entityManager.remove(e);
		this.flush();
	}

	@Override
	public void deleteById(K id) {
		E entity = this.findById(id);
		this.delete(entity);
	}
	
	@Override
	public Long deleteAll() {
		  return (long) entityManager.createQuery("delete from " + entityClass.getName()).executeUpdate();
	}
	
	@Override
	public E findById(K id) {
		return this.entityManager.find(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findAll() {
		return entityManager.createQuery("from " + entityClass.getName()).getResultList();
	}
	
	@Override
	public Long countAll() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(entityClass)));
		
		return entityManager.createQuery(criteriaQuery).getSingleResult().longValue();
	}
	
	
	
	@Override
	public void flush() {
		this.entityManager.flush();
		
	}
}
