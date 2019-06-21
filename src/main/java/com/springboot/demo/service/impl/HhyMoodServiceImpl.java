package com.springboot.demo.service.impl;

import com.springboot.demo.model.HhyMood;
import com.springboot.demo.mq.HhyMoodProducer;
import com.springboot.demo.repository.HhyMoodRepository;
import com.springboot.demo.service.HhyMoodService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * 微信说说服务层实现类
 * @author hehaiyang
 */
@Service
public class HhyMoodServiceImpl implements HhyMoodService {

    @Resource
    private HhyMoodRepository hhyMoodRepository;

    @Override
    public HhyMood save(HhyMood hhyMood) {
        return hhyMoodRepository.save(hhyMood);
    }

    private static Destination destination = new ActiveMQQueue("hhy.queue.asyn.save");

    @Resource
    private HhyMoodProducer hhyMoodProducer;

    @Override
    public String asynSave(HhyMood hhyMood) {
        //向消息队列hhy.queue.asyn.save当中推送消息，消息内容为说说实体
        hhyMoodProducer.sendMessage(destination,hhyMood);
        return "success";
    }
}
