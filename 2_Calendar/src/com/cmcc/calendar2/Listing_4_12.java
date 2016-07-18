package com.cmcc.calendar2;

import javax.print.attribute.standard.JobHoldUntil;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.cmcc.calendar1.PrintInfoJob;

//HourlyCalendar基于小时排除某些分钟来执行
public class Listing_4_12 {
    
    public static void main(String[] args) {
        Listing_4_12 example = new Listing_4_12();
        example.startSchduler();
    }
    
    public void startSchduler() {
        try {
            //Create a default instance of the Scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            
            //Using the NoOpJob, but could have been any
            scheduleJob(scheduler, PrintInfoJob.class);
            scheduler.start();
            
        } catch (Exception e) {
        }
    }

    public void scheduleJob(Scheduler scheduler, Class jobClass) {
        try {
            //Create an instance of the Quartz AnnualCalendar
            HourlyCalendar cal = new HourlyCalendar();
            cal.setMinuteExcluded(28);
            cal.setMinuteExcluded(29);
            
            //Add Calendar to the Scheduler
            scheduler.addCalendar("hourlyExample", cal, true, true);
            
            //trigger will use Calendar to exclude firing times
            SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", Scheduler.DEFAULT_GROUP).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).modifiedByCalendar("hourlyExample").startNow().build();

            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity("hourlyExample", Scheduler.DEFAULT_GROUP).build();
            
            //Associate the trigger with the job int scheduler
            scheduler.scheduleJob(jobDetail, trigger);
            
        } catch (Exception e) {
        }
    }
}
