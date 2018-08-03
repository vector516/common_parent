package com.itheima.bos.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.IStandarDao;
import com.itheima.bos.service.StandardService;
import com.itheima.domain.Standard;

import java.util.List;

@Service
@Transactional
public class StandardServiceImpl implements StandardService {
    @Autowired
    private IStandarDao istandao;

    @Override
    public void save(Standard standard) {
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission("standard:save");
        istandao.save(standard);
    }

    @Override
    public List<Standard> findAll() {
        List<Standard> list = istandao.findAll();
        return list;
    }
    @Override
    public Page<Standard> findByPage(Pageable pageable) {
        Page<Standard> page = istandao.findAll(pageable);
        return page;
    }

    @Override
    public Standard finById(Integer id) {
        Standard standard= istandao.findOne(id);
        return  standard;
    }

}
