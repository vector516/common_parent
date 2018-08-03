package com.itheima.bos.service;

import com.itheima.domain.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MenuService {
    List<Menu> findAll();

    void save(Menu model);

    Page<Menu> findByPage(Pageable pageable);


    List<Menu> findMenu();

}
