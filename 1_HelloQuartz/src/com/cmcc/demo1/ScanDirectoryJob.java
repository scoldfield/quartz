package com.cmcc.demo1;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//第三章：HelloQuartz示例代码
public class ScanDirectoryJob implements Job {

    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //每个job都有它自己的JobDetail
        JobDetail jobDetail = context.getJobDetail();

        String description = jobDetail.getDescription();
        System.out.println(description + new Date());
        JobDataMap dataMap = jobDetail.getJobDataMap();
        String dirName = dataMap.getString("SCAN_DIR");
        if(dirName == null || "".equals(dirName)) {
            throw new JobExecutionException("Directory not configured");
        }
        
        //目录存在
        File dir = new File(dirName);
        if(!dir.exists()) {
            throw new JobExecutionException("Invalid Dir " + dirName);
        }
        
        //Use FileFilter to get only XML files
        FileFilter filter = new FileExtensionFilter(".xml");
        File[] files = dir.listFiles();
        if(files == null || files.length <= 0) {
            System.out.println("No XML files found in " + dir);
            return;
        }
        
        //The number of XML files
        int size = files.length;
        for(int i = 0; i < size; i++) {
            File file = files[i];
            
            //Log something interesting about each file
            File aFile = file.getAbsoluteFile();
            long fileSize = file.length();
            String msg = aFile + "- Size: " + fileSize;
            System.out.println(msg);
        }
    }
}
