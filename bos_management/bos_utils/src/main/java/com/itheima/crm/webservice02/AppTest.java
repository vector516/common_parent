package com.itheima.crm.webservice02;

import java.util.List;

public class AppTest {
    public static void main(String[] args) {

        CustomerServiceImplService customerServiceImplService = new CustomerServiceImplService();
        CustomerServiceImpl customerServiceImplPort = customerServiceImplService.getCustomerServiceImplPort();
        List<Customer> list = customerServiceImplPort.findByFixedAreaIdIsNull();
        List<Customer> list02 = customerServiceImplPort.findByFixedAreaId("dq001");
        System.out.println("list的大小"+list.size());
        System.out.println("list02的大小"+list02.size());
    }




}
