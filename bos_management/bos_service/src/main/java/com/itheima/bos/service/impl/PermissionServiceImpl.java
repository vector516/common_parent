package com.itheima.bos.service.impl;

import com.itheima.bos.dao.PermissionDao;
import com.itheima.bos.service.PermissionService;
import com.itheima.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;


    @Override
    public void save(Permission model) {
        permissionDao.save(model);
    }

    @Override
    public List<Permission> findAll() {

        List<Permission> list = permissionDao.findAll();
        return list;

    }
}
