package com.itheima.mq.listener;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
@Component
public class SmsConsumer implements MessageListener {
    @Override
    public void onMessage(Message message) {
        MapMessage mapMessage=(MapMessage)message;
        try {
            String telephone = mapMessage.getString("telephone");
            String content = mapMessage.getString("content");
            System.out.println("电话是:"+telephone);
            System.out.println("验证码是:"+content);
            //怎么返回成功的消息
            String res = "aaaabbbbccccdddd";// SmsUtils.sendSmsByWebService(customer.getTelephone(), "你好,你的验证码是" + random);



        } catch (JMSException e) {
            e.printStackTrace();
        }


    }
}
