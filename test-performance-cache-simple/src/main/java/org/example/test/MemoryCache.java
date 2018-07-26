package org.example.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.map.LRUMap;

public class MemoryCache<K, T> {
	
	private long timeToLive;
	
	private LRUMap cache;
	
	protected class ElementCache {
		
		public long lastAccessed = System.currentTimeMillis();
		
		public T value;
		
		public ElementCache(T value) {
			this.value = value;
		}
	}
	
	public MemoryCache(final long timeToLive, final long timeInterval, final int maxCacheSize) {
		this.timeToLive = timeToLive * 1000;
		
		cache = new LRUMap(maxCacheSize);
		
		if (timeToLive > 0 && timeInterval > 0) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true) {
						try {
							Thread.sleep(timeInterval * 1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						cleanup();
					}
				}
			});
			t.setDaemon(true);
			t.start();
		}
	}
	
	public void put(K key, T value) {
		synchronized (cache) {
			cache.put(key, new ElementCache(value));
		}
	}
	
	@SuppressWarnings("unchecked")
	public T get(K key) {
		synchronized (cache) {
			ElementCache o = (ElementCache) cache.get(key);
			
			if (o == null) {
				return null;
			} else {
				o.lastAccessed = System.currentTimeMillis();
				return o.value;
			}
		}
	}
	
	public void remove(K key) {
		synchronized (cache) {
			cache.remove(key);
		}
	}
	
	public int size() {
		synchronized (cache) {
			return cache.size();
		}
	}

	@SuppressWarnings("unchecked")
	protected void cleanup() {
		long now = System.currentTimeMillis();
		List<K> keys;
		
		synchronized (cache) {
			MapIterator iterator = cache.mapIterator();
			
			keys = new ArrayList<K>();
			K key = null;
			ElementCache e;
			
			while (iterator.hasNext()) {
				key = (K) iterator.next();
				e = (ElementCache) iterator.getValue();
				
				if (e != null && (now > (timeToLive + e.lastAccessed))) {
					keys.add(key);
				}
			}
		}
		
		for (K k : keys) {
			synchronized (cache) {
				cache.remove(k);
			}
			Thread.yield();
		}

	}

}
