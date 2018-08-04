package org.example.test.scriptlet;

import net.sf.jasperreports.engine.JRAbstractScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

import org.apache.log4j.Logger;

public class MonitorScriptlet extends JRAbstractScriptlet {

	private static final Logger LOGGER = Logger.getLogger(MonitorScriptlet.class);
	
	@Override
	public void beforeReportInit() throws JRScriptletException {
		LOGGER.info("--> ...here!");
		// TODO Auto-generated method stub
	}
	
	@Override
	public void afterReportInit() throws JRScriptletException {
		LOGGER.info("--> ...here!");
		// TODO Auto-generated method stub

	}
	
	@Override
	public void beforePageInit() throws JRScriptletException {
		LOGGER.info("--> ...here!");
		// TODO Auto-generated method stub
	}
	
	@Override
	public void afterPageInit() throws JRScriptletException {
		LOGGER.info("--> ...here!");
		// TODO Auto-generated method stub
	}
	
	@Override
	public void beforeColumnInit() throws JRScriptletException {
		LOGGER.info("--> ...here!");
		// TODO Auto-generated method stub
	}
	
	@Override
	public void afterColumnInit() throws JRScriptletException {
		LOGGER.info("--> ...here!");
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeDetailEval() throws JRScriptletException {
		LOGGER.info("--> ...here!");
		// TODO Auto-generated method stub
	}
	
	@Override
	public void afterDetailEval() throws JRScriptletException {
		LOGGER.info("--> ...here!");
		// TODO Auto-generated method stub
	}

	@Override
	public void afterGroupInit(String groupName) throws JRScriptletException {
		LOGGER.info("--> ...here!");
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeGroupInit(String groupName) throws JRScriptletException {
		LOGGER.info("--> ...here!");
		// TODO Auto-generated method stub
	}

}
