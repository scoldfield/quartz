package com.cmcc.demo1;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

//运行一个简单的Quartz作业调度器
public class SimpleScheduler {

    public static void main(String[] args) {
        SimpleScheduler simpleScheduler = new SimpleScheduler();
        simpleScheduler.startScheduler();
    }
    
    public void startScheduler() {
        Scheduler scheduler = null;
        
        try {
            //Get a Scheduler instance from the Factory
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            
            //Start the scheduler
            scheduler.start();
            System.out.println("Scheduler started at " + new Date());
            
            modifyScheduler(scheduler);
            
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    
    public void modifyScheduler(Scheduler scheduler) {
        try {
            if(!scheduler.isInStandbyMode()) {
                System.out.println("Scheduler standBy now...");
                //pause the scheduler
                scheduler.standby();
            }
            
            //Do sth interesting here...
            System.out.println("am I standby?");
            
            //and then restart it
            scheduler.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
