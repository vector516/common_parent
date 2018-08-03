package com.itheima.bos.service.impl;

import com.itheima.bos.dao.MenuDao;
import com.itheima.bos.dao.PermissionDao;
import com.itheima.bos.dao.RoleDao;
import com.itheima.bos.service.RoleService;
import com.itheima.domain.Menu;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public Page<Role> findByPage(Pageable pageable) {
        Page<Role> page = roleDao.findAll(pageable);
        return page;
    }

    @Override
    public void save(Role model, Integer[] permissionIds, String menuIds) {
        roleDao.save(model);//role从瞬时对象变持久对象,在快照区存在role对象
        if (StringUtils.isNotBlank(menuIds)) {
            String[] menus = menuIds.split(",");
            for (String menuid : menus) {
                //根据创建托管对象,目的是更新第三张表的数据,无需查询数据库

                Menu menu = new Menu();
                menu.setId(Integer.parseInt(menuid));
                model.getMenus().add(menu);
            }

        }

        if (permissionIds != null && permissionIds.length > 0) {
            for (int i = 0; i < permissionIds.length; i++) {
                //根据创建托管对象,目的是更新第三张表的数据,无需查询数据库
                Permission permission =new Permission();
                permission.setId(permissionIds[i]);
                model.getPermissions().add(permission);
            }


        }


    }

    @Override
    public List<Role> findAll() {
        List<Role> list = roleDao.findAll();
        return list;


    }
}
