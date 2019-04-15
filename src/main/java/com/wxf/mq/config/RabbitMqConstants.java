package com.wxf.mq.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author wxf
 * @date 2019/4/12
 * @description rabbitMq配置需要的常量
 */
@Data
@Component
public class RabbitMqConstants {
    //交换机常量

    //转发交换机的bean名称
    public static final String FANOUT_EXCHANGE_BEAN_NAME = "fanoutExchange";
    //转发交换机名称
    public static final String FANOUT_EXCHANGE = "fanoutExchange";

    //转发交换机的bean名称
    public static final String TOPIC_EXCHANGE_BEAN_NAME = "topicExchange";
    //转发交换机名称
    public static final String TOPIC_EXCHANGE = "topicExchange";

    // 延迟交换机
    // 延迟交换机的bean名称
    public static final String  DELAYED_EXCHANGE_BEAN_NAME = "delayedExchange";
    //延迟交换机的名称
    public static final String DELAYED_EXCHANGE = "DELAYED_EXCHANGE";
    //延迟交换机的type  x-delayed-message 固定值
    public static final String DELAYED_EXCHANGE_TYPE = "x-delayed-message";


    //测试交换机的bean名称
   public static final String TEST_EXCHANGE_BEAN_NAME = "testExchange";
    //测试交换机名称
   public static final String TEST_EXCHANGE = "testExchange";


   //路由常量

    // fanout路由bean名称
    public static final String FANOUT_BINDING_BEAN_NAME = "fanoutBinding";

    // direct路由beanm名称
    public static final String DIRECT_BINDING_BEAN_NAME = "directBinding";

   //队列常量

    //fanout队列1的bean1名称
    public static final String FANOUT_QUEUE_BEAN_NAME_1 = "fanoutQueue1";
    //fanout队列1的名称常量
    public static final String FANOUT_QUEUE_1 = "FANOUT_QUEUE1";

    //fanout队列2的bean1名称
    public static final String FANOUT_QUEUE_BEAN_NAME_2 = "fanoutQueue2";
    //fanout队列2的名称常量
    public static final String FANOUT_QUEUE_2 = "FANOUT_QUEUE2";

    //topic队列1的bean1名称
    public static final String TOPIC_QUEUE_BEAN_NAME_1 = "topicQueue1";
    //topic队列1的名称常量
    public static final String TOPIC_QUEUE_1 = "TOPIC_QUEUE_1";

    //topic队列2的bean1名称
    public static final String TOPIC_QUEUE_BEAN_NAME_2 = "topicQueue2";
    //topic队列2的名称常量
    public static final String TOPIC_QUEUE_2 = "TOPIC_QUEUE_2";


    //延迟队列的bean名称
    public static final String DELAYED_QUEUE_BEAN_NAME = "delayedQueue";
    //延迟队列的名称常量
    public static final String DELAYED_QUEUE = "DELAYED_QUEUE";


    //测试队列的bean名称
    public static final String DIRECT_QUEUE_BEAN_NAME = "directQueue";
    //测试队列的名称常量
    public static final String DIRECT_QUEUE = "DIRECT_QUEUE";
}
