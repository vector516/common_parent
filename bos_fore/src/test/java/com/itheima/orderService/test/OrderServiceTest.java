package com.itheima.orderService.test;


import com.itheima.bos.service01.impl.Area;
import com.itheima.bos.service01.impl.Order;
import com.itheima.bos.service01.impl.OrderServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OrderServiceTest {

    @Resource
    private OrderServiceImpl orderService;

    @Test
    public void test(){
        Order order = new Order();
        order.setOrderNum("1232132142");
        orderService.save(order);

    }


    @Test
    public void test01(){
        Area area1 = orderService.findByPCD("北京市", "北京市", "东城区");
        System.out.println(area1);
    }



}
