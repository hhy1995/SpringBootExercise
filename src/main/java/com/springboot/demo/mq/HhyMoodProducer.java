package com.springboot.demo.mq;

import com.springboot.demo.model.HhyMood;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * 消息的生产者
 * @author hehaiyang
 */
@Service
public class HhyMoodProducer {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(Destination destination, final String message){
        jmsMessagingTemplate.convertAndSend(destination,message);
    }

    public void sendMessage(Destination destination, final HhyMood hhyMood){
        jmsMessagingTemplate.convertAndSend(destination,hhyMood);
    }
}
