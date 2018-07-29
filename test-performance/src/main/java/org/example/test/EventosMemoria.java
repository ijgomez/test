package org.example.test;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryNotificationInfo;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;

import javax.management.Notification;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;

public class EventosMemoria {

	static class ReceptorNotificaciones implements NotificationListener {

		@Override
		public void handleNotification(Notification notification, Object handback) {
			String tipo = notification.getType();
			if (tipo.equals(MemoryNotificationInfo.MEMORY_COLLECTION_THRESHOLD_EXCEEDED))
				System.out.println("Umbral de recolecciones excedido");
			if (tipo.equals(MemoryNotificationInfo.MEMORY_THRESHOLD_EXCEEDED))
				System.out.println("Umbral de uso de memoria excedido");

			CompositeData cd = (CompositeData) notification.getUserData();
			MemoryNotificationInfo info = MemoryNotificationInfo.from(cd);
			System.out.println("Pool = " + info.getPoolName() + ", valor = " + info.getCount() + " " + EventosMemoria.toString(info.getUsage()));
		}
	}

	public static String toString(MemoryUsage uso) {
		long libre = uso.getMax() - uso.getUsed();
		return "Free: " + libre + "(" + libre / 1024 + "K) " + uso.toString();
	}

	public static MemoryPoolMXBean findPoolBean(String nombre) {
		nombre = nombre.toLowerCase();
		List<MemoryPoolMXBean> poolBeans = ManagementFactory.getMemoryPoolMXBeans();
		for (MemoryPoolMXBean poolBean : poolBeans){
			//System.out.println(poolBean.getName());
			if (poolBean.getName().toLowerCase().contains(nombre))
				return poolBean;
		}
		return null;
	}

	public static void main(String[] args) {
		// Asociar los eventos
		MemoryMXBean memoria = ManagementFactory.getMemoryMXBean();
		NotificationEmitter emisor = (NotificationEmitter) memoria;
		emisor.addNotificationListener(new ReceptorNotificaciones(), null, null);

		// Configurar los umbrales del pool que nos interesa
		MemoryPoolMXBean oldGen = findPoolBean("Tenured");
		oldGen.setCollectionUsageThreshold(5);
		oldGen.setUsageThreshold(1024 * 1024 * 40); // 40 Mb

		// Comenzar a llenar la memoria
		List<Object> datos = new ArrayList<>();
		while (true) {
			datos.add(new byte[1024 * 1024 * 10]);
			System.out.println(toString(oldGen.getUsage()));
			if (Math.random() > 0.8)
				System.gc();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
		}
	}

}
