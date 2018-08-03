package com.itheima.crm.webservice03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AppTest {

    public static void main(String[] args) {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext_cxf.xml");
        CustomerServiceImpl customerService = (CustomerServiceImpl) ac.getBean("customerService");
        List<Customer> list = customerService.findByFixedAreaIdIsNull();
        List<Customer> list02 = customerService.findByFixedAreaId("dq001");

        System.out.println("list的大小"+list.size());
        System.out.println("list02的大小"+list02.size());
    }       

}
