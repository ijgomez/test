package org.example.test.jdbcpool;

import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.log4j.Logger;

public abstract class ObjectPool {
	
	private static final Logger LOGGER = Logger.getLogger(ObjectPool.class);	

	private long expirationTime;

	private Hashtable<Object, Long> locked;

	private Hashtable<Object, Long> unlocked;

	public ObjectPool() {
		expirationTime = 30000; // 30 seconds
		locked = new Hashtable<Object, Long>();
		unlocked = new Hashtable<Object, Long>();
	}

	protected abstract Object create();

	protected abstract boolean validate(Object o);

	protected abstract void expire(Object o);

	protected synchronized Object checkOut() {
		long now = System.currentTimeMillis();
		Object o;
		
		if (unlocked.size() > 0) {
			Enumeration<Object> e = unlocked.keys();
			while (e.hasMoreElements()) {
				o = e.nextElement();
				if ((now - ((Long) unlocked.get(o)).longValue()) > expirationTime) {
					// object has expired
					unlocked.remove(o);
					expire(o);
					o = null;
				} else {
					if (validate(o)) {
						unlocked.remove(o);
						locked.put(o, new Long(now));
						return (o);
					} else {
						// object failed validation
						unlocked.remove(o);
						expire(o);
						o = null;
					}
				}
			}
		}
		// no objects available, create a new one
		o = create();
		locked.put(o, new Long(now));
		
		LOGGER.trace(Thread.currentThread().getName() + " - " + "TRACE: pool [" + unlocked.size() +", " + locked.size() + "]");
		return (o);
	}

	protected synchronized void checkIn(Object o) {
		locked.remove(o);
		unlocked.put(o, new Long(System.currentTimeMillis()));
	}
}
