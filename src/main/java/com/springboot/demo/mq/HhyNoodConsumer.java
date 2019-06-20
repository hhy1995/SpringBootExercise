package com.springboot.demo.mq;

import org.springframework.jms.annotation.JmsListener;

/**
 * 消费者
 */
public class HhyNoodConsumer {

    @JmsListener(destination = "hhy.queue")
    public void receiveQueue(String text){
        System.out.println("用户发表说说 【" + text +"】成功");
    }
}
