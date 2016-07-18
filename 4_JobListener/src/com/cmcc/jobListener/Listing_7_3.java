package com.cmcc.jobListener;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.cmcc.job.PrintInfoJob;

public class Listing_7_3 {
    
    public static void main(String[] args) {
        Listing_7_3 example = new Listing_7_3();

        try {
            example.startScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void startScheduler() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail jobDetail = JobBuilder.newJob(PrintInfoJob.class).withIdentity("printInfoJob", Scheduler.DEFAULT_GROUP).build();
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("printInfoJob-trigger", Scheduler.DEFAULT_GROUP).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();

        //原来是通过scheduler.addGlobalJobListener(new SimpleJobListener())或addJobListener()方法来为scheduler添加jobListener的
        //现在这个方法以及被取消了...
        
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
