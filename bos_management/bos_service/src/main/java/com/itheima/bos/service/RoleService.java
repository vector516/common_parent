package com.itheima.bos.service;

import com.itheima.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {
    Page<Role> findByPage(Pageable pageable);

    void save(Role model, Integer[] permissionIds, String menuIds);

    List<Role> findAll();
}
