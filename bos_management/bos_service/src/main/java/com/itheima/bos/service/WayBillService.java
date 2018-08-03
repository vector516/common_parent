package com.itheima.bos.service;

import com.itheima.domain.WayBill;

import java.util.List;

public interface WayBillService {
    void save(WayBill model);

    List<WayBill> findAll();

}
