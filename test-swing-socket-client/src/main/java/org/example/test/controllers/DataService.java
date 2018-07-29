package org.example.test.controllers;

import org.example.test.domain.Configuration;

public class DataService<T> {

	public static final DataService<Configuration> CONFIGURATION = new DataService<Configuration>();
	
	private DataService() {

	}
	
	public void save(T t) throws DataServiceException {
		// TODO Auto-generated constructor stub
	}
	
	public T load(Long id) throws DataServiceException {
		// TODO Auto-generated constructor stub
		return null;
	}
	
}
