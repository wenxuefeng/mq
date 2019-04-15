package com.wxf.mq.handler;

import com.wxf.mq.config.RabbitMqConstants;
import com.wxf.mq.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

/**
 * @author wxf
 * @date 2019/4/11
 * @description 发送消息 创建消息生产者Sender。通过注入AmqpTemplate接口的实例来实现消息的发送，
 * AmqpTemplate接口定义了一套针对AMQP协议的基础操作。在Spring Boot中会根据配置来注入其具体实现。
 * 在该生产者，我们会产生一个字符串，并发送到名为hello的队列中
 */
@RestController
@RequestMapping("mq")
@Slf4j
public class SendMq {
    @Autowired
    AmqpTemplate rabbitTemplate;

    /**
     * 简单模式：一个生产者，一个消费者
     */
    @RequestMapping("/hello")
    public void send() {
        String context = "hello " + new Date();
        log.info("发送消息 : " + context);
        rabbitTemplate.convertAndSend("hello", context);
    }


    /**
     * work模式：一个生产者，多个消费者，每个消费者获取到的消息唯一
     */
    @RequestMapping("/workSend")
    public void workSend() {
        User user = null;
        for (int i = 0; i < 5; i++) {
            user = new User();
            user.setName("wxf");
            user.setAge(i);
            log.info("发送消息 : " + user);
            String s = user.toString();
            rabbitTemplate.convertAndSend("hello", s);
        }
    }

    /**
     * Fanout 广播 模式 :对应Fanout交换机的队列都会收到消息
     */
    @RequestMapping("/fanoutSend")
    public void fanoutSend() {
        User user = null;
        for (int i = 0; i < 5; i++) {
            user = new User();
            user.setName("wxf");
            user.setAge(i);
            log.info("发送消息 : " + user);
            String s = user.toString();
            rabbitTemplate.convertAndSend(RabbitMqConstants.FANOUT_EXCHANGE,null,s);
        }
    }

    /**
     *  topic 模式 : 主题模式 匹配模式的路由
     */
    @RequestMapping("/topicSend")
    public void topicSend() {
        User user = null;
        String routingKeyA = "topic.wxf.w";
        for (int i = 0; i < 5; i++) {
            user = new User();
            user.setName("wxf");
            user.setAge(i);
            log.info("发送消息 : " + user);
            String s = user.toString();
            rabbitTemplate.convertAndSend(RabbitMqConstants.TOPIC_EXCHANGE,routingKeyA,s);
        }
    }

    /**
     *  延迟队列 模式
     */
//    @RequestMapping("/delayeSend")
//    public void delayeSend() {
//        User user = new User();
//        user.setName("wxf");
//        user.setAge(21);
//        String msg = user.toString();
//        Long timeStamp = 1555064580000L;
//        Instant instant = Instant.ofEpochMilli(timeStamp);
//        rabbitTemplate.convertAndSend(RabbitMqConstants.DELAYED_EXCHANGE,
//                RabbitMqConstants.DELAYED_QUEUE,
//                msg,
//                new MessagePostProcessor() {
//                    @Override
//                    public Message postProcessMessage(Message message) throws AmqpException {
//                        /* 设置过期时间 */
//                        long diff = Math.abs(Duration.between(
//                                Instant.now(),instant
//                        ).toMillis());
//                        message.getMessageProperties().setHeader("x-delay", diff);
//                        return message;
//                    }
//                }
//            );
//    }


}
