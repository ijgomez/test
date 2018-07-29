package org.example.test.core.domain.base;

import java.io.Serializable;
import java.util.List;

public interface ApplicationDao<E, K extends Serializable> {

	E insert(E e);

	E findById(K id);

	E update(E e);

	void delete(E e);
	
	void deleteById(K id);

	List<E> findAll();
	
	Long countAll();
	
	Long deleteAll();
	
	void flush();
}
