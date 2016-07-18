package com.cmcc.demo1;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

//代码3.5
public class Listing_3_5 {

    public static void main(String[] args) {
        Listing_3_5 example = new Listing_3_5();

        try {
            Scheduler scheduler = example.createScheduler();

            scheduler.start();
            
            /*
             * 通过同一个调度器scheduler来调度相同的任务多次也是可行的(即执行完全相同的两个schedule任务)，只需要设置不同的调度任务之间JobDatail和Trigger的jobName值不同即可
             */
            //Schedule the first job
            System.out.println("================= scanDirJob1 run...");
            example.scheduleJob(scheduler, "scanDirJob1", ScanDirectoryJob.class, "F:\\迅雷下载", 2);     //main方法中调用该类中的其他方法要么使用对象来调用，要么将该方法定义成static方法
            
            //Schedule the second job
            System.out.println("+++++++++++++++++ scanDirJob2 run...");
            example.scheduleJob(scheduler, "scanDirJob2", ScanDirectoryJob.class, "F:\\迅雷下载\\吴悠", 3);
            
            
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /*
     * return an instance of the Scheduler form the factory
     */
    public Scheduler createScheduler() throws SchedulerException {
        return StdSchedulerFactory.getDefaultScheduler();
    }
    
    //Create and Schedule a ScanDirectory with the Scheduler
    public void scheduleJob(Scheduler scheduler, String jobName, Class jobClass, String scanDir, int scanInterval) {
        try {
            System.out.println(jobName + " run... **************************");

            //1. Create a JobDetail for the job
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, Scheduler.DEFAULT_GROUP).build();
            
            //1.1 Configure the directory to scan
            jobDetail.getJobDataMap().put("SCAN_DIR", scanDir);
            
            //2. Create a trigger that fires evey 10 seconds, forever
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName + "-Trigger", Scheduler.DEFAULT_GROUP).startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(scanInterval).repeatForever()).build();

            //3. Associate the trigger with the job in the scheduler
            scheduler.scheduleJob(jobDetail, trigger);
            
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
