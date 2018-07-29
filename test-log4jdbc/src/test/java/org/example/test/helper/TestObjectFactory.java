package org.example.test.helper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang.RandomStringUtils;
import org.example.test.domain.Signal;
import org.example.test.domain.SignalQuality;
import org.example.test.helper.DateHelper;
import org.springframework.stereotype.Component;

@Component
public class TestObjectFactory {

	public Signal buildSignal() {
		Signal signal;
		long timeInMillis;
		
		timeInMillis = Calendar.getInstance().getTimeInMillis();
		
		signal = new Signal();
		signal.setValue(RandomStringUtils.randomAlphanumeric(20));		
		signal.setCreationTime(new Timestamp(timeInMillis));
		signal.setDst(DateHelper.getDSTIndicator(timeInMillis, TimeZone.getDefault()));
		signal.setSignalQuality(SignalQuality.VALID);
		signal.setIecNumber(0);
		signal.setElementFk(24);
		signal.setParentFk(2);
		signal.setSignalInfoFk(22);
		
		return signal;
	}
	
	public List<Signal> buildSignals(int num) {
		List<Signal> signals;
		
		signals = new ArrayList<Signal>();
		for (int i = 0; i < num; i++) {
			signals.add(this.buildSignal());
		}
		
		return signals;
	}

}
