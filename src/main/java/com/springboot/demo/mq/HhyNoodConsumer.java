package com.springboot.demo.mq;

import com.springboot.demo.model.HhyMood;
import com.springboot.demo.service.HhyMoodService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 消费者
 */
@Component
public class HhyNoodConsumer {

    @JmsListener(destination = "hhy.queue")
    public void receiveQueue(String text){
        System.out.println("用户发表说说 【" + text +"】成功");
    }

    @Resource
    private HhyMoodService hhyMoodService;

    @JmsListener(destination = "hhy.queue.asyn.save")
    public void receiveQueue(HhyMood hhyMood){
        hhyMoodService.save(hhyMood);
    }
}
