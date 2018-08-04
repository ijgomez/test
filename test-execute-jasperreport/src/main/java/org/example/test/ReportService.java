package org.example.test;

import java.io.File;
import java.sql.Connection;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

public class ReportService {

	private static final Logger LOGGER = Logger.getLogger(ReportService.class);
	
	public void execute(Connection connection, String reportTemplate, Map<String, Object> parameters) throws JRException {
		
		LOGGER.info("Compile report...");
		JasperReport report = JasperCompileManager.compileReport(reportTemplate);
		
		LOGGER.info("Report Name: " + report.getName());
		
		LOGGER.info("Settings report params...");
		LOGGER.info("Parameters:");
		for (JRParameter parameter : report.getParameters()) {
			if (!parameter.isSystemDefined()) {
				LOGGER.info("P: " + parameter.getName() + " (" + parameter.isForPrompting() + ") " + parameter.getValueClass());
			}
		}

		LOGGER.info("Execute report...");
		JasperPrint print = JasperFillManager.fillReport(report, parameters, connection);
					
		LOGGER.info("Export report to Excel... (by code)");
		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setExporterInput(new SimpleExporterInput(print));
		File outputFile = new File("target/export.xlsx");
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
		SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
		configuration.setDetectCellType(true);
		configuration.setCollapseRowSpan(false);
		exporter.setConfiguration(configuration);
		exporter.exportReport();
	}
}
