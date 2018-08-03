package com.itheima.quartzTest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuartzTest {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:applicationContext-qz.xml");
    }

}
