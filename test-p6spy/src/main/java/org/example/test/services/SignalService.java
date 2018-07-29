package org.example.test.services;

import java.util.List;

import javax.transaction.Transactional;

import org.example.test.criteria.SignalCriteria;
import org.example.test.domain.Signal;
import org.example.test.domain.SignalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SignalService {
	
	@Autowired
	private SignalDao signalDao;
	
	public void insert(Signal signal) {
		this.signalDao.insert(signal);
	}

	public List<Signal> findByCriteria(SignalCriteria criteria) {
		return this.signalDao.findByCriteria(criteria);
	}

}
