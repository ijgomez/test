package org.example.test;

import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.impl.StdSchedulerFactory;

public class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class);

	private SchedulerFactory schedulerFactory;
	
	private String nameJobsGroup = "automaticGroup";

	private void run(String[] args) throws SchedulerException, ParseException {

		LOGGER.info("------- Initializing -------------------");

		// First we must get a reference to a scheduler
		schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();

		LOGGER.info("------- Initialization Complete --------");

		LOGGER.info("------- Scheduling Jobs ----------------");

		// jobs can be scheduled before sched.start() has been called

		// job 1 will run every 20 seconds
		
		JobDetail job;
		CronTrigger trigger;
		Date ft;
		programarTarea1(scheduler);

		// job 2 will run every other minute (at 15 seconds past the minute)
		job = new JobDetail("tarea2", nameJobsGroup, TestJob.class);
		trigger = new CronTrigger("trigger2", nameJobsGroup, "tarea2", nameJobsGroup, "15 0/2 * * * ?");
		scheduler.addJob(job, true);
		ft = scheduler.scheduleJob(trigger);
		LOGGER.info(job.getFullName() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());

		// job 3 will run every other minute but only between 8am and 5pm
		job = new JobDetail("tarea3", nameJobsGroup, TestJob.class);
		trigger = new CronTrigger("trigger3", nameJobsGroup, "tarea3", nameJobsGroup, "0 0/2 8-17 * * ?");
		scheduler.addJob(job, true);
		ft = scheduler.scheduleJob(trigger);
		LOGGER.info(job.getFullName() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());

//		// job 4 will run every three minutes but only between 5pm and 11pm
//		job = new JobDetail("job4", nameJobsGroup, TestJob.class);
//		trigger = new CronTrigger("trigger4", nameJobsGroup, "job4", nameJobsGroup, "0 0/3 17-23 * * ?");
//		scheduler.addJob(job, true);
//		ft = scheduler.scheduleJob(trigger);
//		LOGGER.info(job.getFullName() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());
//
//		// job 5 will run at 10am on the 1st and 15th days of the month
//		job = new JobDetail("job5", nameJobsGroup, TestJob.class);
//		trigger = new CronTrigger("trigger5", nameJobsGroup, "job5", nameJobsGroup, "0 0 10am 1,15 * ?");
//		scheduler.addJob(job, true);
//		ft = scheduler.scheduleJob(trigger);
//		LOGGER.info(job.getFullName() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());
//
//		// job 6 will run every 30 seconds but only on Weekdays (Monday through
//		// Friday)
//		job = new JobDetail("job6", nameJobsGroup, TestJob.class);
//		trigger = new CronTrigger("trigger6", nameJobsGroup, "job6", nameJobsGroup, "0,30 * * ? * MON-FRI");
//		scheduler.addJob(job, true);
//		ft = scheduler.scheduleJob(trigger);
//		LOGGER.info(job.getFullName() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());
//
//		// job 7 will run every 30 seconds but only on Weekends (Saturday and
//		// Sunday)
//		job = new JobDetail("job7", nameJobsGroup, TestJob.class);
//		trigger = new CronTrigger("trigger7", nameJobsGroup, "job7", nameJobsGroup, "0,30 * * ? * SAT,SUN");
//		scheduler.addJob(job, true);
//		ft = scheduler.scheduleJob(trigger);
//		LOGGER.info(job.getFullName() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());

		LOGGER.info("------- Starting Scheduler ----------------");

		// All of the jobs have been added to the scheduler, but none of the
		// jobs
		// will run until the scheduler has been started
		scheduler.start();

		LOGGER.info("------- Started Scheduler -----------------");

		LOGGER.info("------- Waiting five minutes... ------------");
		try {
			// wait five minutes to show jobs
			Thread.sleep(300L * 1000L);
			// executing...
		} catch (Exception e) {
		}

		LOGGER.info("------- Shutting Down ---------------------");

		scheduler.shutdown(true);

		LOGGER.info("------- Shutdown Complete -----------------");

		SchedulerMetaData metaData = scheduler.getMetaData();
		LOGGER.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");

	}

	private void programarTarea1(Scheduler scheduler) throws ParseException, SchedulerException {
		
		String jobName = "tarea1";
		
		JobDetail jobDetail = new JobDetail(jobName, nameJobsGroup, TestJob.class);
		JobDataMap jobDataMap;
		
		jobDataMap = new JobDataMap();
		jobDataMap.put("name", true);
		jobDetail.setJobDataMap(jobDataMap);
		
		CronTrigger trigger = new CronTrigger("trigger1", nameJobsGroup, jobName, nameJobsGroup, "0/20 * * * * ?");
		scheduler.addJob(jobDetail, true);
		Date date = scheduler.scheduleJob(trigger);
		LOGGER.info(jobDetail.getFullName() + " has been scheduled to run at: " + date + " and repeat based on expression: " + trigger.getCronExpression());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new Main().run(args);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
