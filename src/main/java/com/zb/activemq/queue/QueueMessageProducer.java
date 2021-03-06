package com.zb.activemq.queue;

import javax.annotation.Resource;
import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.zb.activemq.PersonInfo;

/**
 * 点对点消息生产者
 * 
 * 作者: gavin.cfm 
 * 日期：2015年9月28日 上午10:07:56
 */
@Component
public class QueueMessageProducer {
    
    /**
     * spring消息发送模版
     */
    @Resource(name="queueJmsTemplate")
    private JmsTemplate jmsTemplate;

    /**
     * 消息目的地
     */
    @Resource(name = "queueDestination")
    private Queue defaultDestination;

    /**
     * 发送消息
     * 
     * 作者: gavin.cfm 
     * 日期：2015年9月28日 上午10:18:54
     * @param personInfo
     */
    public void sendQueueMessage(PersonInfo personInfo) {
        // getJmsTemplate().convertAndSend(personInfo);//如果配置文件中指定了目的地，可以使用这句话发送消息。
        
        System.out.println("点对点 QueueMessageProducer 消息生产者开始发送消息...");
        
        //目的地、模版，都是通过注入方式引入，并不是通过配置bean的方式引入.
        getJmsTemplate().convertAndSend(this.defaultDestination, personInfo);
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public Queue getDefaultDestination() {
        return defaultDestination;
    }

    public void setDefaultDestination(Queue defaultDestination) {
        this.defaultDestination = defaultDestination;
    }

}
