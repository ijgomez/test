package org.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Trace
	public Customer getCustomer(Long id){
		return this.customerDao.getCustomer(id);
	}
	
	@Trace
	public void execute(Long id) {
		
	}
}
