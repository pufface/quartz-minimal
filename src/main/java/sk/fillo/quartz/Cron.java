package sk.fillo.quartz;

import javax.servlet.http.HttpServletRequest;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.ee.servlet.QuartzInitializerServlet;

public class Cron {

	public String schedule(HttpServletRequest req) throws SchedulerException {
		SchedulerFactory factory = (SchedulerFactory) req.getServletContext()
				.getAttribute(QuartzInitializerServlet.QUARTZ_FACTORY_KEY);
		Scheduler scheduler = factory.getScheduler();
		Trigger trigger = trigger();
		JobDetail job = jobDetail();
		if (scheduler.checkExists(job.getKey())) {
			scheduler.rescheduleJob(trigger.getKey(), trigger);
			return "Job was rescheduled";
		} else {
			scheduler.scheduleJob(job, trigger);
			return "Job was scheduled";
		}
	}

	private JobDetail jobDetail() {
		return JobBuilder.newJob(HelloJob.class).withIdentity("dummyJobName", "group1").build();
	}

	private Trigger trigger() {
		return TriggerBuilder.newTrigger().withIdentity("dummyTriggerName", "group1")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();
	}

}
