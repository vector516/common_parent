package com.itheima.bos.service.impl;

import com.itheima.bos.dao.TakeTimeDao;
import com.itheima.bos.service.TakeTimeService;
import com.itheima.domain.TakeTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TakeTimeServiceImpl implements TakeTimeService {
    @Autowired
    private TakeTimeDao takeTimeDao;

    @Override
    public List<TakeTime> findAll() {
        List<TakeTime> list = takeTimeDao.findAll();
        return list;
    }
}
