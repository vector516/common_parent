package com.itheima.bos.service.impl;

import com.itheima.bos.dao.WayBillDao;
import com.itheima.bos.service.WayBillService;
import com.itheima.domain.WayBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WayBillServiceImpl implements WayBillService {
    @Autowired
    private WayBillDao wayBillDao;

    @Override
    public void save(WayBill model) {
        wayBillDao.save(model);
    }

    @Override
    public List<WayBill> findAll() {
        List<WayBill> list = wayBillDao.findAll();
        return list;

    }
}
