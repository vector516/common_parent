package com.itheima.bos.service.impl;

import com.itheima.bos.dao.MenuDao;
import com.itheima.bos.service.MenuService;
import com.itheima.domain.Menu;
import com.itheima.domain.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;


    @Override
    public List<Menu> findAll() {
        List<Menu> list = menuDao.findByParentMenuIsNull();
        return list;
    }

    @Override
    public void save(Menu model) {
        menuDao.save(model);
    }

    @Override
    public Page<Menu> findByPage(Pageable pageable) {
        Page<Menu> page = menuDao.findAll(pageable);
        return page;
    }

    @Override
    public List<Menu> findMenu() {
        //根据用户id查询菜单数据
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Menu> list = null;
        if (user.getUsername().equals("admin")) {
            list = menuDao.findAll();
        } else {
            list = menuDao.findByUser(user.getId());

        }
        return list;

    }
}
