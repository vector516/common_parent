package com.itheima.bos.service;

import com.itheima.domain.SubArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubAreaService {
    List<SubArea> findAll();

    void save(SubArea model);

    List<SubArea> findByDecideId(String decidedzoneid);

    List<Object> findSubAreas();

    Page<SubArea> findByPage(Pageable pageable);
}
