package com.itheima.bos_fore.utils;



import javax.mail.MessagingException;

public class Test {
    public static void main(String[] args) throws MessagingException {
    MailUtils.sendMail("wuyunlong_516@163.com","http://192.168.20.74:8080","问候");
    System.out.println("搞定........");

    }
}
