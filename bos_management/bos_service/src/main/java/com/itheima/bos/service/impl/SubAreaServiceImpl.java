package com.itheima.bos.service.impl;

import com.itheima.bos.dao.SubAreaDao;
import com.itheima.bos.service.SubAreaService;
import com.itheima.domain.SubArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubAreaServiceImpl implements SubAreaService {
    @Autowired
    private SubAreaDao subAreaDao;


    @Override
    public List<SubArea> findAll() {
        List<SubArea> list = subAreaDao.findAll();
        return list;
    }

    @Override
    public void save(SubArea model) {
        subAreaDao.save(model);
    }

    @Override
    public List<SubArea> findByDecideId(String decidedzoneid) {
        List<SubArea> list = subAreaDao.findByDecideId(decidedzoneid);

        return list;


    }

    @Override
    public List<Object> findSubAreas() {
        List<Object> list = subAreaDao.findSubAreas();
        return list;

    }

    @Override
    public Page<SubArea> findByPage(Pageable pageable) {
        Page<SubArea> page = subAreaDao.findAll(pageable);
        return page;
    }
}
