package com.cmcc.quartz.demo1;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

//快速入门Quartz
public class Demo1 {
    
    public static void main(String[] args) throws SchedulerException {
        /*
         * 1、调度器Scheduler的实例化。
         *      1.1 通过SchedulerFactory来创建一个实例。
         *      1.2 Scheduler实例化后，可以启动(start)、暂停(stand-by)、停止(shutdown)。
         *          1.2.1 scheduler被停止(shutdown)后，除非重新实例化，否则不能重新启动
         *          1.2.2 当scheduler被启动，且不处于暂停(stand-by)状态，trigger才会被触发(job才会被执行)
         */
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        
        //2、创建一个Job实例，并绑定到自定义的job
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("MyJob", "group1").build();

        //3、通过SimpleTrigger定义调度规则：马上启动，每2秒运行一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();

        //4、在调度器中注册我们的trgger
        scheduler.scheduleJob(jobDetail, trigger);
        
        //5、调度启动。scheduler只有在start后才会真正触发trigger从而启动任务
        scheduler.start();
    }
}
