package org.example.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jasperreports.engine.export.JRExportProgressMonitor;

public class SimpleJRExportProgressMonitor implements JRExportProgressMonitor {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleJRExportProgressMonitor.class);
	
	public void afterPageExport() {
		LOGGER.info("--> ... here!");
	}

}
