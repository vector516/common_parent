package com.itheima.bos.service.impl;

import com.itheima.bos.dao.UserDao;
import com.itheima.bos.service.UserService;
import com.itheima.domain.Role;
import com.itheima.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void save(User model, Integer[] roleids) {
        userDao.save(model);

        if(roleids!=null&&roleids.length>0){
            for(Integer roleid:roleids){
                //更新第三方表使用托管态即可
                Role role = new Role();
                role.setId(roleid);
                model.getRoles().add(role);
            }
        }

    }

    @Override
    public Page<User> findByPage(Pageable pageable) {
        Page<User> page = userDao.findAll(pageable);
        return page;

    }
}
