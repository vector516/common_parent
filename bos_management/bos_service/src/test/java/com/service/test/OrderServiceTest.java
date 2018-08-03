package com.service.test;

import com.itheima.bos.dao.OrderDao;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext111.xml")
public class OrderServiceTest {
    @Resource
    OrderDao orderDao;

//    @Resource
//    OrderServiceImpl orderServiceImpl;

    }



