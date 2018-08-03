package com.itheima.bos.service;

import com.itheima.domain.Permission;

import java.util.List;

public interface PermissionService {
    void save(Permission model);

    List<Permission> findAll();

}
