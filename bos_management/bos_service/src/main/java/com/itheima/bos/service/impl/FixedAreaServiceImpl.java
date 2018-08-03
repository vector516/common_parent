package com.itheima.bos.service.impl;

import com.itheima.bos.dao.CourierDao;
import com.itheima.bos.dao.FixedAreaDao;
import com.itheima.bos.dao.TakeTimeDao;
import com.itheima.bos.service.FixedAreaService;
import com.itheima.domain.Courier;
import com.itheima.domain.FixedArea;
import com.itheima.domain.TakeTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FixedAreaServiceImpl implements FixedAreaService {
    @Autowired
    private FixedAreaDao fixedAreaDao;

    @Override
    public void save(FixedArea model) {
        fixedAreaDao.save(model);
    }

    @Override
    public Page<FixedArea> findByPage(Pageable pageable) {
        Page<FixedArea> page = fixedAreaDao.findAll(pageable);
        return page;
    }


    @Autowired
    private CourierDao courierDao;

    @Autowired
    private TakeTimeDao takeTimeDao;

    @Override
    public void associationCourierToFixedArea(String id, Integer courierId, Integer takeTimeId) {
        //定区关联快递员
        FixedArea fixedArea = fixedAreaDao.findOne(id);
        Courier courier = courierDao.findOne(courierId);
        fixedArea.getCouriers().add(courier);
        //快递员关联时间
        TakeTime takeTime = takeTimeDao.findOne(takeTimeId);
        courier.setTakeTime(takeTime);

    }


}
