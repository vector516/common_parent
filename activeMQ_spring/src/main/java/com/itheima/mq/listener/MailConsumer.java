package com.itheima.mq.listener;

import com.itheima.mq.utils.MailUtils;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
@Component
public class MailConsumer implements MessageListener {
    @Override
    public void onMessage(Message message) {

        MapMessage mapMessage=(MapMessage)message;
        try {
            String mail = mapMessage.getString("mail");
            String mailContent = mapMessage.getString("mailContent");
            System.out.println("邮箱是:"+mail);
            System.out.println("邮件内容是:"+mailContent);

            MailUtils.sendMail(mail, mailContent, "账号激活");
            System.out.println("邮件发送成功....activeMQ.....");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
