package org.example.test;

import java.lang.management.MemoryUsage;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;



public class AccesoMXBean {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		/* 1. A�adir tools.jar de JDK (%JDK_HOME%\lib\tools.jar)
		 * 2. Indicar la ubicacion de attach.dll (o attach.so)
		 *   -Djava.library.path=<path de la libreria>
		 *   -Djava.library.path=%JDK_HOME%\jre\bin
		 */

		List<VirtualMachineDescriptor> vms = VirtualMachine.list();
		
		VirtualMachineDescriptor vmdHoteles = null;
		
		for (VirtualMachineDescriptor vmd : vms) {
			System.out.println("------------------------------------------");
			System.out.println(vmd.displayName() + " " + vmd.id());
			
			//Conectarse a una VM remota.
			VirtualMachine vm = VirtualMachine.attach(vmd);
			
			Properties properties = vm.getAgentProperties();
			for (Object clave : properties.keySet()) {
				String nombre = (String) clave;
				System.out.println("\t" +  nombre + "=" + properties.getProperty(nombre));
			}

			// Localizar una VM que nos interese
			if (vmd.displayName().contains("Hoteles")) {
				vmdHoteles = vmd;
			}
		}
		System.out.println("-----------------------------------------------------------------------");
		VirtualMachine vmHoteles = VirtualMachine.attach(vmdHoteles);
		
		String direccion = vmHoteles.getAgentProperties().getProperty("com.sun.management.jmxremote.localConnectorAddress");
		JMXServiceURL url = new JMXServiceURL(direccion);
		JMXConnector conector = JMXConnectorFactory.connect(url);
		
		try {
			MBeanServerConnection servidor = conector.getMBeanServerConnection();
			
			Set<ObjectName> nombres = servidor.queryNames(null, null);
			for (ObjectName nombre : nombres) {
				System.out.println("Bean Name: " + nombre);
			}
			System.out.println("-----------------------------------------------------------------------");	
			ObjectName nombreBean;
			nombreBean = ObjectName.getInstance("com.sun.management:type=HotSpotDiagnostic");
			//HotSpotDiagnosticMXBean beanDiagnostico = JMX.newMBeanProxy(servidor, nombreBean, HotSpotDiagnosticMXBean.class);
			//beanDiagnostico.dumpHeap("c:\\heap.log", true);
			System.out.println("-----------------------------------------------------------------------");
			
			//
			ObjectName nombreBeansMemoria;
			nombreBeansMemoria = ObjectName.getInstance("java.lang:type=MemoryPool,name=Tenured Gen");
			System.out.println("UsageThresholdCount=" + servidor.getAttribute(nombreBeansMemoria, "UsageThresholdCount"));
			
			 CompositeData cd  = (CompositeData) servidor.getAttribute(nombreBeansMemoria, "PeakUsage");
			 MemoryUsage memoryUse = MemoryUsage.from(cd);
			 System.out.println("PeakUsage=" + memoryUse);
			
			
		} finally {
			conector.close();
		}
		System.out.println("-----------------------------------------------------------------------");
	}

}
