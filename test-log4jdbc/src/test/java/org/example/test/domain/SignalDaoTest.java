package org.example.test.domain;

import static org.junit.Assert.*;

import java.util.List;

import org.example.test.domain.Signal;
import org.example.test.domain.SignalDao;
import org.example.test.helper.TestObjectFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class SignalDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private SignalDao signalDao;

	@Autowired
	private TestObjectFactory objectFactory;

	@Ignore
	public void testInsert() {
		Signal entity;
		
		entity = this.objectFactory.buildSignal();

		signalDao.insert(entity);

		assertNotNull(entity.getId());

		List<Signal> results = signalDao.list();
		assertEquals(1, results.size());
		
	}
	
	@Ignore
	public void testInserts() {
		int num = 100;
		List<Signal> signals;
		
		signals = this.objectFactory.buildSignals(num);
		
		this.signalDao.insert(signals);
		
		List<Signal> results = signalDao.list();
		assertEquals(num, results.size());
	}
	
	@Test
	public void testInsertsNative() {
		int num = 100;
		List<Signal> signals;
		
		signals = this.objectFactory.buildSignals(num);
		
		this.signalDao.insertBatchNativeSQL(signals);
		
		List<Signal> results = signalDao.list();
		assertEquals(num, results.size());
	}
	
	//
	// @Test
	// public void testInsert1DayYear() {
	// Calendar c;
	//
	// c = stringToCalendar("01/01/2013 00:00:00", "dd/MM/yyyy HH:mm:ss");
	//
	// assertNotNull(signalDao.insert(super.buildSignal("1.0",
	// c.getTimeInMillis())));
	//
	// super.setComplete();
	// }

	// @Test
	// public void testInsertDST_IV() {
	// Calendar c;
	//
	// c = stringToCalendar("31/03/2013 00:00:00", "dd/MM/yyyy HH:mm:ss");
	//
	// for (int i = 0; i < 24; i++) {
	// c.add(Calendar.HOUR_OF_DAY, +1);
	// assertNotNull(signalDao.insert(super.buildSignal("1.0",
	// c.getTimeInMillis())));
	// }
	//
	//
	//
	// super.setComplete();
	// }

	// @Test
	// public void testInsertDST_VI() {
	// Calendar c;
	//
	// TimeZone tz = TimeZone.getTimeZone("America/Tijuana");
	//
	// c = stringToCalendar("27/10/2013 00:00:00", "dd/MM/yyyy HH:mm:ss");
	// for (int i = 0; i < 24; i++) {
	// c.add(Calendar.HOUR_OF_DAY, +1);
	// assertNotNull(signalDao.insert(super.buildSignal("1.0",
	// c.getTimeInMillis())));
	// }
	//
	// super.setComplete();
	// }

	// @Test
	// public void testInserts1DayYear() {
	// Calendar c = stringToCalendar("02/01/2013 00:00:00",
	// "dd/MM/yyyy HH:mm:ss");
	//
	// List<Signal> signals = new ArrayList<Signal>();
	// signals.add(super.buildSignal("2.0", c.getTimeInMillis()));
	//
	//
	// c = Calendar.getInstance();
	//
	// signals.add(super.buildSignal("3.0", c.getTimeInMillis()));
	//
	// signalDao.insert(signals);
	//
	// assertNull(null);
	//
	// super.setComplete();
	// }

	// @Test
	// public void testInsertPlus6M() {
	// Calendar c;
	//
	// c = Calendar.getInstance();
	// c.add(Calendar.MONTH, 6);
	//
	// assertNotNull(signalDao.insert(super.buildSignal("3.0",
	// c.getTimeInMillis())));
	//
	// super.setComplete();
	// }

	// @Test
	// public void testInsertWithDistinctTimezone() {
	// Calendar c;
	//
	// c = Calendar.getInstance();
	// c.setTimeZone(TimeZone.getTimeZone("America/Tijuana"));
	//
	// assertNotNull(signalDao.insert(super.buildSignal("4.0",
	// c.getTimeInMillis())));
	//
	// super.setComplete();
	// }

	// @Test
	// public void testLoadAll() {
	// List<Signal> loadAll = signalDao.loadAll();
	//
	// assertNotNull(loadAll);
	//
	// for (Signal signal : loadAll) {
	//
	// TimeZone tz = TimeZone.getDefault();
	// Calendar c = Calendar.getInstance();
	//
	// c.setTimeInMillis(signal.getCreationTime().getTime());
	//
	// if (signal.getDst()) {
	// c.set(Calendar.DST_OFFSET, c.getTimeZone().getDSTSavings());
	// }
	//
	// System.out.println(DateFormatUtils.format(c.getTimeInMillis(),
	// "dd/MM/yyyy HH:mm:ss zZ") + " " + c.getTimeZone().getID());
	// }
	// }

	// @Test
	// public void testFindByCriteria() throws Exception {
	// SignalCriteria criteria;
	//
	// criteria = new SignalCriteria();
	//
	// criteria.setFechaInicio(DateUtils.parseDate("04/07/2013 00:00", new
	// String[]{"dd/MM/yyyy HH:mm"}));
	// criteria.setFechaFin(new Date(criteria.getFechaInicio().getTime() +
	// 86400000));
	// criteria.setFirstResult(0);
	// criteria.setMaxResults(100);
	//
	// List<Signal> findByCriteria = signalDao.findByCriteria(criteria);
	//
	// assertNotNull(findByCriteria);
	//
	//
	//
	// // for (Signal signal : findByCriteria) {
	// // System.out.println(DateFormatUtils.format(signal.getCreationTime(),
	// "dd/MM/yyyy HH:mm:ss zZ"));
	// //
	// // }
	// }
	//
	// @Test
	// public void testUpdateSignals() throws Exception {
	// List<Signal> signals;
	//
	// // signals = signalDao.loadAll();
	// // for (Signal signal : signals) {
	// // assertEquals(SignalQuality.MANUAL, signal.getSignalQuality());
	// // }
	//
	// signals = signalDao.loadAll();
	// for (Signal signal : signals) {
	// signal.setSignalQuality(SignalQuality.VALID);
	// signalDao.update(signal);
	// }
	//
	// // signals = signalDao.loadAll();
	// // for (Signal signal : signals) {
	// // assertEquals(SignalQuality.VALID, signal.getSignalQuality());
	// // }
	// super.setComplete();
	// //TODO
	// }

}
