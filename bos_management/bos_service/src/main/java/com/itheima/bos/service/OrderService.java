package com.itheima.bos.service;

import com.itheima.domain.Area;
import com.itheima.domain.Order;

public interface OrderService {
    public void save(Order order);
    public Area findByPCD(String provice, String city, String distinct);
}
