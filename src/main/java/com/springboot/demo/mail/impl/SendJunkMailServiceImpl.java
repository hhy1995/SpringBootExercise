package com.springboot.demo.mail.impl;

import com.springboot.demo.mail.SendJunkMailService;
import com.springboot.demo.model.HhyUser;
import com.springboot.demo.service.HhyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * 发送用户邮件服务
 * @author hehaiyang
 */
@Service
public class SendJunkMailServiceImpl implements SendJunkMailService {

    //邮件发送接口。在spring boot的starter模块中已经为此提供了自动化配置，直接@Autowired自动注入就好
    @Autowired
    JavaMailSender mailSender;
    @Resource
    private HhyUserService hhyUserService;

    @Value("${spring.mail.username}")
    private String from;
    public static final Logger logger = LogManager.getLogger(SendJunkMailServiceImpl.class);

    @Override
    public boolean sendJunkMail(List<HhyUser> hhyUserList) {
        try{
            if(hhyUserList == null || hhyUserList.size() <= 0 ) return Boolean.FALSE;
            for(HhyUser hhyUser : hhyUserList){
                MimeMessage mimeMessage = this.mailSender.createMimeMessage();
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                //邮件发送方
                message.setFrom(from);
                //邮件主题
                message.setSubject("Quartz定时器发送邮件测试");
                //邮件接收方
                message.setTo("haiyanghe@mail.nwpu.edu.cn");
                //邮件内容
                message.setText(hhyUser.getName() + " ,邮件测试内容");
                //发送邮件
                this.mailSender.send(mimeMessage);
            }
        }catch(Exception ex){
            logger.error("sendJunkMail error and hhyUser=%s", hhyUserList, ex);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
