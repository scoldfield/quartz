package com.cmcc.calendar1;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;


//Quartz中Calendar接口的使用：使用AnnualCalendar来排除银行节日
public class Listing_4_10 {
    
    public static void main(String[] args) {
        Scheduler scheduler = null;
        try {
            Listing_4_10 example = new Listing_4_10();
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            example.startScheduler(scheduler, PrintInfoJob.class);
            
            System.out.println("Scheduler starting up...");
            scheduler.start();
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void startScheduler(Scheduler scheduler, Class jobClass) {
        try {
            
            //Create an instance of the Quartz AnnualCalendar
            AnnualCalendar calendar = new AnnualCalendar();
            
            //exclude March 17th
            Calendar gCal = GregorianCalendar.getInstance();    //下面的也可以
//            Calendar gCal = Calendar.getInstance();
            gCal.set(Calendar.MONTH, Calendar.MARCH);
            gCal.set(Calendar.DATE, 17);
            
            calendar.setDayExcluded(gCal, true);
            
            //Add to scheduler, replace existing, update triggers
//            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.addCalendar("bankHolidays", calendar, true, true);
            
            /*
             * set up a trigger to start firing now, repeat forever
             * and have (3000ms) between each firing
             */
            SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("bankHolidays" + "-Triggrt", Scheduler.DEFAULT_RECOVERY_GROUP).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).modifiedByCalendar("bankHolidays").startNow().build();

            JobDetail jobDetail = JobBuilder.newJob(PrintInfoJob.class).withIdentity("printInfoJob", Scheduler.DEFAULT_GROUP).build();
            
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
