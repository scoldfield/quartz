package com.cmcc.calendar2;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.quartz.Calendar;
import org.quartz.impl.calendar.BaseCalendar;

//自定义Calendar，直接继承BaseCalendar即可
//示例：HourlyCalendar，从每小时中排除某些分钟。也可以通过CronTrigger来实现
public class HourlyCalendar extends BaseCalendar {
    
    //Array of Integer from 0 to 59
    private List excludedMinutes = new ArrayList();
    
    public HourlyCalendar() {
        super();
    }
    public HourlyCalendar(Calendar baseCalendar) {
        super(baseCalendar);
    }
    
    public boolean isMinuteExcluded(int minute) {
        Iterator it = excludedMinutes.iterator();
        while(it.hasNext()) {
            Integer excludeMinute = (Integer) it.next();
            if(excludeMinute == minute) {
                return true;
            }
            
            continue;
        }
        return false;
    }
    
    public void setMinutesExcluded(List minutes) {
        if(minutes == null) {
            return;
        }
        
        excludedMinutes.addAll(minutes);
    }
    
    public void setMinuteExcluded(int minute) {
        if(isMinuteExcluded(minute)) {
            return;
        }
        
        excludedMinutes.add(new Integer(minute));
    }
    
    public boolean isTimeIncluded(long timeStamp) {
        if(super.isTimeIncluded(timeStamp) == false) {
            return false;
        }
        
        java.util.Calendar cal = getJavaCalendar(timeStamp);
        int minute = cal.get(java.util.Calendar.MINUTE);
        
        return !(isMinuteExcluded(minute));
        
    }
    
    public long getNextIncludedTime(long timeStamp) {
        //Call base calendar implementation first
        long baseTime = super.getNextIncludedTime(timeStamp);
        if((baseTime > 0) && (baseTime > timeStamp)) {
            timeStamp = baseTime;
        }
        
        //Get timestamp for 00:00:00
        long newTimeStamp = buildHoliday(timeStamp);
        java.util.Calendar cal = getJavaCalendar(newTimeStamp);
        int minute = cal.get(java.util.Calendar.MINUTE);
        
        if(isMinuteExcluded(minute) == false) {
            return timeStamp;       //return the original value
        }
        
        while(isMinuteExcluded(minute) == true) {
            cal.add(java.util.Calendar.MINUTE, 1);
        }
        
        return cal.getTime().getTime();
    }
    
    
    
    
    /*
     * 低版本BaseCalendar中有的方法
     */
    /**
     * <p>
     * Utility method. Return the date of excludeDate. The time fraction will
     * be reset to 00.00:00.
     * </p>
     */
    static public Date buildHoliday(Date excludedDate) {
 
        java.util.Calendar cl = java.util.Calendar.getInstance();
        java.util.Calendar clEx = java.util.Calendar.getInstance();
        clEx.setTime(excludedDate);
 
        cl.setLenient(false);
        cl.clear();
        cl.set(clEx.get(java.util.Calendar.YEAR), clEx
                .get(java.util.Calendar.MONTH), clEx
                .get(java.util.Calendar.DATE));
 
        return cl.getTime();
    }
    
    /**
     * <p>
     * Utility method. Return just the date of excludeDate. The time fraction
     * will be reset to 00.00:00.
     * </p>
     */
    static public long buildHoliday(long timeStamp) {
 
        return buildHoliday(new Date(timeStamp)).getTime();
    }
    
    /**
     * <p>
     * Utility method. Return a java.util.Calendar for timeStamp.
     * </p>
     * 
     * @param timeStamp
     * @return Calendar
     */
    static public java.util.Calendar getJavaCalendar(long timeStamp) {
 
        java.util.Calendar cl = java.util.Calendar.getInstance();
        cl.setTime(new Date(timeStamp));
        return cl;
     
    }
}
