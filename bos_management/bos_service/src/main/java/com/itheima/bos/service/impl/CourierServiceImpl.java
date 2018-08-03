package com.itheima.bos.service.impl;

import com.itheima.bos.dao.CourierDao;
import com.itheima.bos.service.CourierService;
import com.itheima.domain.Courier;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourierServiceImpl implements CourierService {
    @Autowired
    private CourierDao courierDao;

    @Override
    public void save(Courier courier) {
        courierDao.save(courier);
    }

    @Override
    public Page<Courier> findByPage(Specification<Courier> spec, Pageable pageable) {
        Page<Courier> page = courierDao.findAll(spec,pageable);
        return page;
    }

    @Override
    @RequiresPermissions("courier:delete")
    public void deleteAll(String ids) {
        if(StringUtils.isNotBlank(ids)) {
            String[] strings = ids.split(",");
            for (String s : strings) {
                int id = Integer.parseInt(s);

                courierDao.update(id);

            }
        }


    }

    @Override
    public List<Courier> listajax() {
        List<Courier> list = courierDao.findByDeltagIsNull();
        return list;
    }

}
