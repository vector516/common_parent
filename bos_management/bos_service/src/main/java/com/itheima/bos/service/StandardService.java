package com.itheima.bos.service;

import com.itheima.domain.Standard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StandardService {

    void save(Standard standard);

    List<Standard> findAll();

    Page<Standard> findByPage(Pageable pageable);

    Standard finById(Integer id);
}
