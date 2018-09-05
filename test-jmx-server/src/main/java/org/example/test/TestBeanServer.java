package org.example.test;

import java.lang.management.ManagementFactory;
import java.util.Set;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

import org.example.test.domain.Device;

public class TestBeanServer {

	public void start() throws MalformedObjectNameException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, InterruptedException {
		System.out.println("Creating Server....");
		
		
		System.out.println("Loading MBean Server....");
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		
		System.out.println("Registring MBean....");
		// Construct the ObjectName for the Device MBean we will register
		ObjectName mbeanName = new ObjectName("org.examples.test.jmx.server:type=Device");
		server.registerMBean(new Device(), mbeanName);
		
		System.out.println("List MBean....");
		Set<ObjectInstance> instances = server.queryMBeans(mbeanName, null);
		
		ObjectInstance instance = (ObjectInstance) instances.toArray()[0];
		
		System.out.println("Class Name: " + instance.getClassName());
		System.out.println("Object Name: " + instance.getObjectName());
		
		
		System.out.println("Waiting forever..."); 
        Thread.sleep(Long.MAX_VALUE); 
	}
	
}
