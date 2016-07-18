package com.cmcc.quartz.demo1;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {

    @Override
    public void execute(JobExecutionContext jobCtx) throws JobExecutionException {
        System.out.println("MyJob运行啦..." + jobCtx.getTrigger().toString() + "reiggered. time is" + new Date());
    }
}
