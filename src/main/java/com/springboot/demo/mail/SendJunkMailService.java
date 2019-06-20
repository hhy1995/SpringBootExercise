package com.springboot.demo.mail;

import com.springboot.demo.model.HhyUser;

import java.util.List;

/**
 * 发送用户邮件服务
 * @author hehaiyang
 */
public interface SendJunkMailService {
    boolean sendJunkMail(List<HhyUser> hhyUser);
}
