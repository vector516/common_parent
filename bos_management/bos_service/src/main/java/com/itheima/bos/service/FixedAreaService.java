package com.itheima.bos.service;

import com.itheima.domain.FixedArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FixedAreaService {
    void save(FixedArea model);
    public Page<FixedArea> findByPage(Pageable pageable);

    void associationCourierToFixedArea(String id, Integer courierId, Integer takeTimeId);
}
