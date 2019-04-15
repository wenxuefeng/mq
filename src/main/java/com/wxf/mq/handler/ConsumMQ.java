package com.wxf.mq.handler;

import com.wxf.mq.config.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wxf
 * @date 2019/4/11
 * @description 消费mq 创建消息消费者Receiver。通过@RabbitListener注解定义该类对hello队列的监听，
 * 并用@RabbitHandler注解来指定对消息的处理方法。所以，该消费者实现了对hello队列的消费，消费操作为输出消息的字符串内容
 */
@Component
@Slf4j
public class ConsumMQ {
    /**
     * 简单模式和work模式的消费者1
     * queues 监听的队列名称
     * @param hello
     */
    @RabbitListener(queues = "hello")
    @RabbitHandler
    public void consum1(String hello) {
       log.info("消费者1消费消息:"+hello);
    }

    /**
     * 简单模式和work模式的消费者2
     * @param hello
     */
    @RabbitListener(queues = "hello")
    @RabbitHandler
    public void consum2(String hello) {
        log.info("消费者2消费消息:"+hello);
    }

    @RabbitListener(queues = RabbitMqConstants.FANOUT_QUEUE_1)
    @RabbitHandler
    public void consumFanout1A(String msg) {
        log.info("Fanout1A消费消息:"+msg);
    }

    @RabbitListener(queues = RabbitMqConstants.FANOUT_QUEUE_2)
    @RabbitHandler
    public void consumFanout2A(String msg) {
        log.info("Fanout2A消费消息:"+msg);
    }

    @RabbitListener(queues = RabbitMqConstants.FANOUT_QUEUE_1)
    @RabbitHandler
    public void consumFanout1B(String msg) {
        log.info("Fanout1B消费消息:"+msg);
    }

    @RabbitListener(queues = RabbitMqConstants.FANOUT_QUEUE_2)
    @RabbitHandler
    public void consumFanout2B(String msg) {
        log.info("Fanout2B消费消息:"+msg);
    }


    @RabbitListener(queues = RabbitMqConstants.TOPIC_QUEUE_1)
    @RabbitHandler
    public void consumTopic1(String msg) {
        log.info("topic1消费消息:"+msg);
    }

    @RabbitListener(queues = RabbitMqConstants.TOPIC_QUEUE_2)
    @RabbitHandler
    public void consumTopic2(String msg) {
        log.info("topic2消费消息:"+msg);
    }

//    @RabbitListener(queues = RabbitMqConstants.DELAYED_QUEUE)
//    @RabbitHandler
//    public void consumDelaye(String msg) {
//        log.info("延迟队列消费消息:"+msg);
//    }


}
