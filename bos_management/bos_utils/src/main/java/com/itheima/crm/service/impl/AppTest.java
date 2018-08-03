package com.itheima.crm.service.impl;

import java.util.List;

public class AppTest {
    public static void main(String[] args) {
        CustomerServiceImplService customerServiceImplService = new CustomerServiceImplService();
        CustomerServiceImpl customerServiceImplPort = customerServiceImplService.getCustomerServiceImplPort();
        List<Customer> list= customerServiceImplPort.findAll();
        System.out.println(list.size());

    }


}
