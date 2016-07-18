package com.cmcc.jobListener;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;


/*
 * 一个简单的JobListener实例
 */
public class SimpleJobListener implements JobListener {

    Logger logger = Logger.getLogger(this.getClass()); 
    
    //获取JobListener的名字
    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    //JobDetail即将被执行时调用该方法
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        String jobDescription = context.getJobDetail().getDescription();
        logger.info(jobDescription + " is about to be executed");
    }

    //JobDetail即将被执行，但是被TriggerListener否决时调用该方法
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        String description = context.getJobDetail().getDescription();
        logger.info(description + " was vetoed and not executed!");
    }

    //JobDetail被执行后执行该方法
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException arg1) {
        String description = context.getJobDetail().getDescription();
        logger.info(description + " was executed");
    }

}
