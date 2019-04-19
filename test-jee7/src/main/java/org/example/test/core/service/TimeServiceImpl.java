package org.example.test.core.service;

import java.util.Calendar;

import javax.ejb.Stateless;

@Stateless
public class TimeServiceImpl implements TimeService {

	/* (non-Javadoc)
	 * @see org.example.test.core.service.TimeService#getTime()
	 */
	@Override
	public String getTime() {
		System.out.println(this);
		return Calendar.getInstance().getTime().toString();
	}
}
