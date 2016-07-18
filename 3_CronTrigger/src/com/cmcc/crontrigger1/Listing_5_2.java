package com.cmcc.crontrigger1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.print.attribute.standard.PrinterInfo;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

//CronTriggrt应用：在每天的从下午 2 点到下午的 7:59 间的每分钟触发一次；
//倘若你希望这个计划直到下一天才开始，并且只执行两天，你就可以用 CronTrigger 的 setStartTime() 和 setEndTime() 方法来形成一个 "定时箱" 来触发。
public class Listing_5_2 {
    
    public static void main(String[] args) {
        Listing_5_2 exam = new Listing_5_2();
        exam.runScheduler();
    }
    
    public void runScheduler() {
        Scheduler scheduler = null;
        
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            
            JobDetail jobDetail = JobBuilder.newJob(PrintInfoJob.class).withIdentity("printInfoJob", Scheduler.DEFAULT_GROUP).build();
            
            Date startDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2016-03-17").getTime());
            Date endDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2016-03-18").getTime());
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("printInfoJob-trigger", Scheduler.DEFAULT_GROUP).startAt(startDate).endAt(endDate).withSchedule(CronScheduleBuilder.cronSchedule("0 * 14-19 * * ?")).build();
            
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
            
        } catch (Exception e) {
        }
    }
}
