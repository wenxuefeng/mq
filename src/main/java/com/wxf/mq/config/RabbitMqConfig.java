package com.wxf.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wxf
 * @date 2019/4/11
 * @description rabbitMq配置类
 */
@Component
public class RabbitMqConfig {

    //存在多个交换机和多个队列 需要在 @Bean(name = "")中加上名称区分 否则会报错


    //交换机 类型
    //routing_key==路由键
    //Direct：direct 类型的行为是"先匹配, 再投送". 即在绑定时设定一个 routing_key, 消息的routing_key 匹配时, 才会被交换器投送到绑定的队列中去.
    //Direct<直接>：1对1-----一个消息只能被一个消费者消费
    //Topic：按规则转发消息（最灵活） 1对多-----一个消息可以被多个消费者消费
    //Fanout：转发消息到所有绑定队列 广播
    //Headers：设置 header attribute 参数类型的交换机


    /**
     * 订阅交换机 适用场景 如 群发信息
     * @return
     */
    @Bean(name = RabbitMqConstants.FANOUT_EXCHANGE_BEAN_NAME)
    public Exchange fanoutExchange() {
        //交换机构造参数 String name, boolean durable, boolean autoDelete
        //String name 交换机名称
        //boolean durable 重启的时候是否保留这个交换机 true保留 false不保留
        //boolean autoDelete 交换机中没有数据是否删除这个交换机 true删除 false不删除
        Exchange exchange = new FanoutExchange(RabbitMqConstants.FANOUT_EXCHANGE,true,false);
        return exchange;
    }

    /**
     * topicExchange topic交换机
     * @return
     */
    @Bean(name = RabbitMqConstants.TOPIC_EXCHANGE_BEAN_NAME)
    public Exchange topicExchange() {
        Exchange exchange = new TopicExchange(RabbitMqConstants.TOPIC_EXCHANGE,true,false);
        return exchange;
    }


    /**
     * 延迟队列交换机
     *
     * @return the exchange
     */
//    @Bean(name = RabbitMqConstants.DELAYED_EXCHANGE_BEAN_NAME)
//    public CustomExchange delayExchange() {
//        Map<String, Object> args = new HashMap<>();
//        args.put("x-delayed-type", "direct");
//        CustomExchange customExchange = new CustomExchange(RabbitMqConstants.DELAYED_EXCHANGE,
//                RabbitMqConstants.DELAYED_EXCHANGE_TYPE, true, false, args);
//        customExchange.setDelayed(true);
//        return customExchange;
//    }



    /**
     * direct模式交换机  适用场景 异步通知  比如 商品订单下单成功 异步通知到库存系统减库存
     * 交换机的类型为 direct
     * direct 类型的行为是"先匹配, 再投送". 即在绑定时设定一个 routing_key, 消息的routing_key 匹配时, 才会被交换器投送到绑定的队列中去.
     * @return 返回交换机 注入spring中
     */
    @Bean(name = RabbitMqConstants.TEST_EXCHANGE_BEAN_NAME)
    public Exchange directExchange() {
        //Map<String, Object> args = new HashMap<>();
        Exchange exchange = new DirectExchange(RabbitMqConstants.TEST_EXCHANGE, true, false);
        return exchange;
    }

    /**
     * fanout队列1
     * @return
     */
    @Bean(name = RabbitMqConstants.FANOUT_QUEUE_BEAN_NAME_1)
    public Queue fanoutQueue1(){
        //参数
        // String name 队列名称
        // boolean durable 启的时候是否保留这个队列 true保留 false不保留
        Queue queue = new Queue(RabbitMqConstants.FANOUT_QUEUE_1,true);
        return queue;
    }

    /**
     * fanout队列2
     * @return
     */
    @Bean(name = RabbitMqConstants.FANOUT_QUEUE_BEAN_NAME_2)
    public Queue fanoutQueue2(){
        Queue queue = new Queue(RabbitMqConstants.FANOUT_QUEUE_2,true);
        return queue;
    }

    /**
     * 将fanout队列1 绑定到 fanoutExchange交换机上
     * 注意 Exchange 没有指明类型的交换机不能直接绑定队列
     * Exchange 是所有交换机的父类 Exchange只能通过绑定路由键绑定队列
     * Exchange 的 fanout模式 下 队列不需要绑定路由键
     * 队列直接帮交换机 需强转 如 这里 fanoutExchange交换机类型是Exchange 需强转成FanoutExchange类型 即可直接绑定到指定的交换机上
     * @return
     */
    @Bean
    public Binding fanoutBinding1() {
        //bind 绑定队列
        //to 到哪个交换机上
        return BindingBuilder.bind(fanoutQueue1()).to((FanoutExchange) fanoutExchange());
    }

    /**
     * 将fanout队列2 绑定到 fanoutExchange交换机上
     * @return
     */
    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(fanoutQueue2()).to((FanoutExchange) fanoutExchange());
    }


    /**
     * direct队列
     * @return
     */
    @Bean(name = RabbitMqConstants.DIRECT_QUEUE_BEAN_NAME)
    public Queue directQueue(){
        Queue queue = new Queue(RabbitMqConstants.DIRECT_QUEUE,true);
        return queue;
    }

    /**
     * 将directQueue队列绑定到 direct交换机上 指明路由键
     * @return
     */
    @Bean(name = RabbitMqConstants.DIRECT_BINDING_BEAN_NAME)
    public Binding directBinding() {
        //bind 绑定队列
        //to 到哪个交换机上
        //with 路由键 表明是哪个路由的
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(RabbitMqConstants.DIRECT_QUEUE).noargs();
    }


    /**
     * topic队列1
     * @return
     */
    @Bean(name = RabbitMqConstants.TOPIC_QUEUE_BEAN_NAME_1)
    public Queue topicQueue1(){
        Queue queue = new Queue(RabbitMqConstants.TOPIC_QUEUE_1,true);
        return queue;
    }

    /**
     * topic队列2
     * @return
     */
    @Bean(name = RabbitMqConstants.TOPIC_QUEUE_BEAN_NAME_2)
    public Queue topicQueue2(){
        Queue queue = new Queue(RabbitMqConstants.TOPIC_QUEUE_2,true);
        return queue;
    }

    /**
     * 将topic队列1 绑定到 topicExchange交换机上
     * topic.# .# 代表任意个单词
     * @return
     */
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("topic.#").noargs();
    }

    /**
     * 将topic队列1 绑定到 topicExchange交换机上
     * topic.* * 仅代表一个单词
     * @return
     */
    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("topic.*").noargs();
    }


    /**
     * 延迟队列
     * @return
     */
    @Bean(name = RabbitMqConstants.DELAYED_QUEUE_BEAN_NAME)
    public Queue delayeQueue(){
        Queue queue = new Queue(RabbitMqConstants.DELAYED_QUEUE,true);
        return queue;
    }


    /**
     * 将延迟队列 绑定到 delayeExchange交换机上
     * @return
     */
//    @Bean(name = "delayeBinding")
//    public Binding delayeBinding() {
//        return BindingBuilder.bind(delayeQueue()).to(delayExchange()).with(RabbitMqConstants.DELAYED_QUEUE).noargs();
//    }




}
