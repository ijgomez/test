package org.example.test.ds.core.services;

import org.springframework.stereotype.Service;

@Service("integrationService")
public class IntegrationServiceImpl implements IntegrationService {

	@Override
	public Long currentTimeMillis() {
		return System.currentTimeMillis();
	}
	
}
