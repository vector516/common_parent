package com.itheima.mq.utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.util.Properties;

public class MailUtils {
	public static String activeUrl="http://localhost:8080/CustomerAction_activeCode.action";

	public static void main(String[] args) throws MessagingException {
		sendMail("wuyunlong_516@163.com","hahahahahahaha","生日快乐");
		System.out.println("搞定....");
	}
	public static void sendMail(String email, String emailMsg,String subject)
			throws AddressException, MessagingException {
		// 1.创建一个程序与邮件服务器会话对象 Session
		Properties props = new Properties();
		//设置发送的协议
		props.setProperty("mail.transport.protocol", "SMTP");

		//设置发送邮件的服务器service@store.com------>localhost
		props.setProperty("mail.host", "smtp.qq.com");
		props.setProperty("mail.smtp.auth", "true");// 指定验证为true

		//qq-->hllrnpblvdoqbjaa
		//crunzezmtpjhbjgi
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.port","465");



		// 创建验证器
		Authenticator auth = new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				//设置发送人的帐号和密码---->service@store.com
				return new PasswordAuthentication("506074625@qq.com", "hllrnpblvdoqbjaa");
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);

		//设置发送者
		message.setFrom(new InternetAddress("506074625@qq.com"));

		//设置发送方式与接收者
		message.setRecipient(RecipientType.TO, new InternetAddress(email));

		//设置邮件主题
		message.setSubject(subject);

		//设置邮件内容
		message.setContent(emailMsg, "text/html;charset=utf-8");
		// 3.创建 Transport用于将邮件发送
		Transport.send(message);
	}
}
