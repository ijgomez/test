package org.example.test;

import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {

	public Customer getCustomer(Long id) {
		Customer c;
		
		c = new Customer();
		c.setId(id);
		
		return c;
		
	}

}
