package com.cmcc.schedulerListener;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

/*
 * 一个简单的SchedulerListener
 */
public class SimpleSchedulerListener implements SchedulerListener {

    Logger logger = Logger.getLogger(SimpleSchedulerListener.class);


    @Override
    public void jobAdded(JobDetail jobDetail) {
        
    }

    @Override
    public void jobDeleted(JobKey arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void jobPaused(JobKey arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void jobResumed(JobKey arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void jobUnscheduled(TriggerKey arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void jobsPaused(String arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void jobsResumed(String arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void schedulerInStandbyMode() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void schedulerShuttingdown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void schedulerStarted() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void schedulerStarting() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void schedulingDataCleared() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void triggerPaused(TriggerKey arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void triggerResumed(TriggerKey arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void triggersPaused(String arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void triggersResumed(String arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void jobScheduled(Trigger arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void schedulerError(String arg0, SchedulerException arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void schedulerShutdown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void triggerFinalized(Trigger arg0) {
        // TODO Auto-generated method stub
        
    }

}
