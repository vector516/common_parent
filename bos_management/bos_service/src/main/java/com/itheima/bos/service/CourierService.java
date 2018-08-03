package com.itheima.bos.service;

import com.itheima.domain.Courier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CourierService {
    void save(Courier courier);
    public Page<Courier> findByPage(Specification<Courier> spec, Pageable pageable);

    void deleteAll(String ids);

    List<Courier> listajax();

}
