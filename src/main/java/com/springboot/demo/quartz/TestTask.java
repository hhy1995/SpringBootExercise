package com.springboot.demo.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 定时器类
 * @author hehaiyang
 */
public class TestTask {
    //日志对象
    private static final Logger logger = LogManager.getLogger(TestTask.class);

    public void run(){
        logger.info("---定时器(TestTask)开始运行---");
    }


}
