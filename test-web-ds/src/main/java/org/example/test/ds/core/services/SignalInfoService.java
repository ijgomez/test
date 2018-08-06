package org.example.test.ds.core.services;

import java.util.List;

import org.example.test.ds.core.domain.SignalInfo;
import org.example.test.ds.core.domain.SignalInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignalInfoService {

	@Autowired
	private SignalInfoDao signalInfoDao;
	
	@Transactional
	public List<SignalInfo> listAll() {
		return this.signalInfoDao.listAll();
	}
	
}
