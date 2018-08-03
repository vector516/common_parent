package com.itheima.bos.service;

import com.itheima.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    void save(User model, Integer[] roleids);

    Page<User> findByPage(Pageable pageable);
}
