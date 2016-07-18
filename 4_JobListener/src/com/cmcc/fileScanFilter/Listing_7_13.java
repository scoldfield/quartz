package com.cmcc.fileScanFilter;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.jobs.FileScanJob;

//部署FileScanJob
public class Listing_7_13 {

    public static void main(String[] args) {
        Listing_7_13 example = new Listing_7_13();
        try {
            Scheduler scheduler = example.createScheduler();
            example.scheduleJob(scheduler);
            scheduler.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    
    protected Scheduler createScheduler() throws SchedulerException {
        return StdSchedulerFactory.getDefaultScheduler();
    }
    
    protected void scheduleJob(Scheduler scheduler) throws SchedulerException {
        //Store the FileScanListener instance
        scheduler.getContext().put("SimpleFileScanListener", new SimpleFileScanListener());
        
        //Create a JobDetail for the FileScanJob
        JobDetail jobDetail = JobBuilder.newJob(FileScanJob.class).withIdentity("FileScanJob", Scheduler.DEFAULT_GROUP).build();
        
        //The FileScanJob needs some parameters
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put(FileScanJob.FILE_NAME, "D:\\hehe.txt");
        jobDataMap.put(FileScanJob.FILE_SCAN_LISTENER_NAME, "SimpleFileScanListener");
        
        //Create a Trigger and register the job
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity("SimpleTrigger", Scheduler.DEFAULT_GROUP).startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever()).build();
        
        scheduler.scheduleJob(jobDetail, simpleTrigger);
    }
}
