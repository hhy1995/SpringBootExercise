package com.springboot.demo.quartz;

import com.springboot.demo.mail.SendJunkMailService;
import com.springboot.demo.model.HhyUser;
import com.springboot.demo.service.HhyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时器类
 * @author hehaiyang
 */
@Component
@Configurable
@EnableScheduling
public class SendMailQuartz {
    private static final Logger logger = LogManager.getLogger(SendMailQuartz.class);

    @Resource
    private SendJunkMailService sendJunkMailService;
    @Resource
    private HhyUserService hhyUserService;

    //每五秒钟执行一次
    @Scheduled(cron = "*/1000000 * *  * * * ")
    public void reportCurrentByCon(){
        List<HhyUser> userList = hhyUserService.findAll();
        if (userList == null || userList.size() <= 0){
            return ;
        }
        sendJunkMailService.sendJunkMail(userList);
        logger.info("---定时器(SendMailQuartz)开始运行---");
    }
}
