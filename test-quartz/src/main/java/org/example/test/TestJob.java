package org.example.test;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements Job {

	private static final Logger LOGGER = Logger.getLogger(TestJob.class);

	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		LOGGER.debug(context.getJobDetail().getJobDataMap().get("name"));
		
		LOGGER.info("");
//		LOGGER.info("Launch: " + context);

		
		LOGGER.info(context.getJobDetail().getName() + " - Siguiente Ejecucion: " + context.getNextFireTime());
	}



}
