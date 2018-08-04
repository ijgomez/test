package org.example.test;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.export.JRExportProgressMonitor;

public class SimpleJRExportProgressMonitor implements JRExportProgressMonitor {

	private static final Logger LOGGER = Logger.getLogger(SimpleJRExportProgressMonitor.class);
	
	public void afterPageExport() {
		LOGGER.info("--> ... here!");
	}

}
