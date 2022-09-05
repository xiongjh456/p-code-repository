package com.dxs.delay.retry.queue.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 延迟重试队列配置类
 * @className  DelayRetryQueueConfig
 * @author  Mr.Xiong
 * @date  2022/04/11
 */
@Configuration
public class DelayRetryQueueConfig {
   /** x-dead-letter-exchange*/
   private static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
   /** x-dead-letter-routing-key*/
   private static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
   /** x-message-ttl*/
   private static final String X_MESSAGE_TTL = "x-message-ttl";
   /** 同步存储返回的code队列*/
   @Value("${gov-delay.queue.queues.synCountryDelayRetry.queue}")
   private String govSynCountryStoreCodeQueue;
   /** 同步存储返回的code交换机*/
   @Value("${gov-delay.queue.queues.synCountryDelayRetry.exchange}")
   private String govSynCountryStoreCodeExchange;
   /** 同步存储返回的code路由key*/
   @Value("${gov-delay.queue.queues.synCountryDelayRetry.topic}")
   private String govSynCountryStoreCodeRoutingKey;
    /** 延迟队列延迟时间*/
    @Value("${gov-delay.queue.queues.synCountryDelayRetry.delay-time}")
    private Integer delayTime;
   /** 同步存储返回的code重试队列*/
   private static String retryGovSynCountryStoreCodeQueue;
   /** 同步存储返回的code重试交换机*/
   private static String retryGovSynCountryStoreCodeExchange;
   /** 同步存储返回的code重试队列路由key*/
   private static String retryGovSynCountryStoreCodeRoutingKey;
   /** 同步存储返回的code重试队列的死信队列*/
   private static String dlqGovSynCountryStoreCodeRetryQueue;
   /** 同步存储返回的code重试队列的死信队列交换机*/
   private static String dlqGovSynCountryStoreCodeRetryExchange;
   /** 同步存储返回的code重试队列的死信队列路由key*/
   private static String dlqGovSynCountryStoreCodeRetryRoutingKey;

   @PostConstruct
   public void init() {
       String strFormat1 = "%s_%s";
       String strFormat2 = "%s.%s";
       String retry = "retry";
       String dlq = "dlq";
       // 重试队列相关常量初始化
       retryGovSynCountryStoreCodeQueue = String.format(strFormat1, retry, govSynCountryStoreCodeQueue);
       retryGovSynCountryStoreCodeExchange = String.format(strFormat1, retry, govSynCountryStoreCodeExchange);
       retryGovSynCountryStoreCodeRoutingKey = String.format(strFormat2, retry, govSynCountryStoreCodeRoutingKey);
       // 重试队列绑定的死信队列相关常量初始化
       dlqGovSynCountryStoreCodeRetryQueue = String.format(strFormat1, dlq, govSynCountryStoreCodeQueue);
       dlqGovSynCountryStoreCodeRetryExchange = String.format(strFormat1, dlq, govSynCountryStoreCodeExchange);
       dlqGovSynCountryStoreCodeRetryRoutingKey = String.format(strFormat2, dlq, govSynCountryStoreCodeRoutingKey);
   }

   /**
    * 存储返回的code交换机bean
    * @methodName  storeCodeExchange
    * @param
    * @return  org.springframework.amqp.core.DirectExchange
    * @throws
    * @author  Mr.Xiong
    * @date  2022/04/11
    */
   @Bean
   public DirectExchange storeCodeExchange() {
       return new DirectExchange(govSynCountryStoreCodeExchange, true, false);
   }

   /**
    * 存储返回的code队列bean
    * @methodName  storeCodeQueue
    * @param
    * @return  org.springframework.amqp.core.Queue
    * @throws
    * @author  Mr.Xiong
    * @date  2022/04/11
    */
   @Bean
   public Queue storeCodeQueue() {
       Map<String,Object> params = new HashMap<>(16);
       params.put(X_DEAD_LETTER_EXCHANGE, dlqGovSynCountryStoreCodeRetryExchange);
       params.put(X_DEAD_LETTER_ROUTING_KEY, dlqGovSynCountryStoreCodeRetryRoutingKey);
       return new Queue(govSynCountryStoreCodeQueue, true, false, false, params);
   }

   /**
    * 绑定队列交换机
    * @methodName  storeCodeBinding
    * @param storeCodeQueue
    * @param storeCodeExchange
    * @return  org.springframework.amqp.core.Binding
    * @throws
    * @author  Mr.Xiong
    * @date  2022/04/11
    */
   @Bean
   public Binding storeCodeBinding(@Qualifier("storeCodeQueue") Queue storeCodeQueue,
                                   @Qualifier("storeCodeExchange") DirectExchange storeCodeExchange) {
       return BindingBuilder.bind(storeCodeQueue).to(storeCodeExchange).with(govSynCountryStoreCodeRoutingKey);
   }

   /**
    * 存储返回的code重试队列交换机bean
    * @methodName  storeCodeRetryExchange
    * @param
    * @return  org.springframework.amqp.core.DirectExchange
    * @throws
    * @author  Mr.Xiong
    * @date  2022/04/11
    */
   @Bean
   public DirectExchange storeCodeRetryExchange() {
       return new DirectExchange(retryGovSynCountryStoreCodeExchange, true, false);
   }

   /**
    * 存储返回的code重试队列bean
    * @methodName  storeCodeRetryQueue
    * @param
    * @return  org.springframework.amqp.core.Queue
    * @throws
    * @author  Mr.Xiong
    * @date  2022/04/11
    */
   @Bean
   public Queue storeCodeRetryQueue() {
       Map<String,Object> params = new HashMap<>(16);
       params.put(X_DEAD_LETTER_EXCHANGE, govSynCountryStoreCodeExchange);
       params.put(X_DEAD_LETTER_ROUTING_KEY, govSynCountryStoreCodeRoutingKey);
       params.put(X_MESSAGE_TTL, delayTime);
       return new Queue(retryGovSynCountryStoreCodeQueue, true, false, false, params);
   }

   /**
    * 绑定队列交换机
    * @methodName  storeCodeRetryBinding
    * @param storeCodeRetryQueue
    * @param storeCodeRetryExchange
    * @return  org.springframework.amqp.core.Binding
    * @throws
    * @author  Mr.Xiong
    * @date  2022/04/11
    */
   @Bean
   public Binding storeCodeRetryBinding(@Qualifier("storeCodeRetryQueue") Queue storeCodeRetryQueue,
                                        @Qualifier("storeCodeRetryExchange") DirectExchange storeCodeRetryExchange) {
       return BindingBuilder.bind(storeCodeRetryQueue).to(storeCodeRetryExchange).with(retryGovSynCountryStoreCodeRoutingKey);
   }

   /**
    * 存储返回的code重试队列的死信队列
    * @methodName  dlxStoreCodeExchange
    * @param
    * @return  org.springframework.amqp.core.DirectExchange
    * @throws
    * @author  Mr.Xiong
    * @date  2022/04/11
    */
   @Bean
   public DirectExchange dlxStoreCodeRetryExchange() {
       return new DirectExchange(dlqGovSynCountryStoreCodeRetryExchange, true, false);
   }

   /**
    * 存储返回的code重试队列的死信队列bean
    * @methodName  dlxStoreCodeRetryQueue
    * @param
    * @return  org.springframework.amqp.core.Queue
    * @throws
    * @author  Mr.Xiong
    * @date  2022/04/11
    */
   @Bean
   public Queue dlxStoreCodeRetryQueue() {
       return new Queue(dlqGovSynCountryStoreCodeRetryQueue, true, false, false);
   }

   /**
    * 绑定队列交换机
    * @methodName  dlxStoreCodeRetryBinding
    * @param dlxStoreCodeRetryQueue
    * @param dlxStoreCodeRetryExchange
    * @return  org.springframework.amqp.core.Binding
    * @throws
    * @author  Mr.Xiong
    * @date  2022/04/11
    */
   @Bean
   public Binding dlxStoreCodeRetryBinding(@Qualifier("dlxStoreCodeRetryQueue") Queue dlxStoreCodeRetryQueue,
                                           @Qualifier("dlxStoreCodeRetryExchange") DirectExchange dlxStoreCodeRetryExchange) {
       return BindingBuilder.bind(dlxStoreCodeRetryQueue).to(dlxStoreCodeRetryExchange).with(dlqGovSynCountryStoreCodeRetryRoutingKey);
   }

}
