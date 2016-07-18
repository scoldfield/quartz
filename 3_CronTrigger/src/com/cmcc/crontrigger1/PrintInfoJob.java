package com.cmcc.crontrigger1;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

//打印Job的相关基本明细
public class PrintInfoJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //Every job has its own job detail
        JobDetail jobDetail = context.getJobDetail();
        
        //The name and ground are defined in the job detail
        String jobDescription = jobDetail.getDescription();
        System.out.println("jobDescription = " + jobDescription);
        
        //The name of this class configured for the job
        Class jobClass = jobDetail.getJobClass();
        System.out.println("JobClass = " + jobClass);
        
        //Log the time of the job started
        Date fireTime = context.getFireTime();
        System.out.println("Fire Time = " + fireTime);
        
        //Log the next fire time
        Date nextFireTime = context.getNextFireTime();
        System.out.println("Next Fire time = " + nextFireTime);
    }

}
