package com.cmcc.fileScanFilter;

import java.io.File;
import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.quartz.jobs.FileScanListener;

/*
 * Quartz中的一个特殊的Listener
 * 这个监听器不像别的，因为它是为特定目的而设计的：同框架所带的一个工具 Job 一起用的。
 * 这个监听器就是 org.quartz.jobs.FileScanListener 接口，它显式的设计为 FileScanJob 所用的，这一 Job 也在 org.quartz.jobs 包中。
 * FileScanJob 检查某一指定文件的 lastModifiedDate。当某人改变了这个文件，这个 Job 就调用 FileScanListener 的 fileUpdated() 方法。
 */
public class SimpleFileScanListener implements FileScanListener {

    private Logger logger = Logger.getLogger(this.getClass());
    
    @Override
    public void fileUpdated(String fileName) {
        File file = new File(fileName);
        long lastModified = file.lastModified();
        Timestamp modified = new Timestamp(lastModified);   //将毫秒级的long型时间数据转换成正常时间
        
        System.out.println(fileName + " was changed at " + modified);
        logger.info(fileName + " was changed at " + modified);
    }

}
