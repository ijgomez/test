package org.example.test;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.MemoryNotificationInfo;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.Arrays;
import java.util.List;

import javax.management.Notification;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;

public class Memoria {
	
	static class ReceptorNotificaciones implements NotificationListener {
		
		@Override
		public void handleNotification(Notification notification, Object handback) {

			String type = notification.getType();
			if (type.equals(MemoryNotificationInfo.MEMORY_COLLECTION_THRESHOLD_EXCEEDED)) {
				System.out.println(">>>>>> Umbral de recolecciones excedido <<<<<<<");
			}
			if (type.equals(MemoryNotificationInfo.MEMORY_THRESHOLD_EXCEEDED)) {
				System.out.println(">>>>>> Umbral de uso de memoria excedido <<<<<<<");
			}
		}
	}

	public static void main(String[] args) {
		Runtime r = Runtime.getRuntime();

		System.out.println("Memoria Libre (bytes) " + r.freeMemory());
		// -Xms
		System.out.println("Memoria Total (bytes) " + r.totalMemory());
		// -Xmx
		System.out.println("Memoria Maxima (bytes) " + r.maxMemory());
		
		
		System.out.println("------------------------------------------------------");
		/* Usando MBeans */
		
		//Gestion de la memoria
		List<MemoryManagerMXBean> memoryBeans = ManagementFactory.getMemoryManagerMXBeans();
		
		for (MemoryManagerMXBean bean : memoryBeans) {
			System.out.println(bean.getName() + " --> " + Arrays.toString(bean.getMemoryPoolNames()) + " " + bean.isValid());
		}
		System.out.println("------------------------------------------------------");
		/**/
		MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
		
		System.out.println("Head");
		mostrarZona(memory.getHeapMemoryUsage());
		
		System.out.println("Otros");
		mostrarZona(memory.getNonHeapMemoryUsage());
		
		
		NotificationEmitter emisor = (NotificationEmitter) memory;
		emisor.addNotificationListener(new ReceptorNotificaciones(), null, null);
		
		//Invocacion del GC.
		memory.gc();
		
		System.out.println("------------------------------------------------------");
		
		List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
		
		for (GarbageCollectorMXBean gcBean : gcBeans) {
			System.out.println(gcBean.getName() + " recolecciones = " + gcBean.getCollectionCount() + " tiempo total (ms) = " + gcBean.getCollectionTime());
		}
						
		System.out.println("------------------------------------------------------");
		
		List<MemoryPoolMXBean> poolBeans = ManagementFactory.getMemoryPoolMXBeans();
		
		for (MemoryPoolMXBean poolBean : poolBeans) {
			System.out.println("Pool " + poolBean.getName() + " type " + poolBean.getType());
			System.out.println("Uso Maximo");
			mostrarZona(poolBean.getPeakUsage());
			System.out.println("Uso Actual");
			mostrarZona(poolBean.getUsage());
			
			System.out.println("�Soporta eventos de colleciones? " + poolBean.isCollectionUsageThresholdSupported());
			System.out.println("�Soporta eventos de uso? " + poolBean.isUsageThresholdSupported());
		
			//Estableciendo umbrales para notificacion.
//			poolBean.setUsageThreshold(3); //en bytes
//			poolBean.setCollectionUsageThreshold(3);
//			poolBean.isUsageThresholdExceeded();
//			poolBean.isCollectionUsageThresholdExceeded();
			
			
			if (poolBean.isUsageThresholdSupported()) {
				poolBean.setUsageThreshold(3); //en bytes
			}
		}
		
		System.out.println("------------------------------------------------------");
		
		
		
		System.out.println("------------------------------------------------------");
		
	}
	
	public static void mostrarZona(MemoryUsage uso) {
		/*
		 *                                              -Xmx
		 * |----------------------------------------------|
		 * |########---------------|
		 * <------->             -Xms
		 *   used
		 * <---------------------->
		 *      committed
		 */
		System.out.println("Tama�o Inicial (bytes): " + uso.getInit());
		System.out.println("Tama�o Ocupado (bytes): " + uso.getUsed());
		System.out.println("Tama�o Asignado (bytes): " + uso.getCommitted());
		System.out.println("Tama�o Maximo (bytes): " + uso.getMax());
	}

}
