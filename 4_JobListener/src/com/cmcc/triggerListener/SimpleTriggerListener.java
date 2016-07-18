package com.cmcc.triggerListener;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;

/*
 * 一个简单的TriggerListener
 */
public class SimpleTriggerListener implements TriggerListener {

    Logger logger = Logger.getLogger(this.getClass());
    
    private String name;
    
    public void setName(String name) {
        this.name = name;
    }

    //获取triggerListener这个监听器的名字
    @Override
    public String getName() {
        return name;
    }
    
    //当与监听器相关联的 Trigger 被触发，Job 上的 execute() 方法将要被执行时，Scheduler 就调用这个方法
    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        String description = trigger.getDescription();
        logger.info(description + "was fired");
    }
    
    //在 Trigger 触发后，Job 将要被执行时由 Scheduler 调用这个方法。TriggerListener 给了一个选择去否决 Job 的执行。假如这个方法返回 true，这个 Job 将不会为此次 Trigger 触发而得到执行
    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        String description = trigger.getDescription();
        logger.info(description + "eas not vetoed");
        return false;
    }
    
    //Scheduler 调用这个方法是在 Trigger 错过触发时
    @Override
    public void triggerMisfired(Trigger trigger) {
        String description = trigger.getDescription();
        logger.info(description + " misfired");
    }

    //Trigger 被触发并且完成了 Job 的执行时，Scheduler 调用这个方法
    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, CompletedExecutionInstruction arg2) {
        String description = trigger.getDescription();
        logger.info(description + " is complete");
    }

}
