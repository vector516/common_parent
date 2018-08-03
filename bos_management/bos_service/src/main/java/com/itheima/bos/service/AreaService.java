package com.itheima.bos.service;

import com.itheima.domain.Area;
import com.itheima.domain.Standard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface AreaService {
    void importfile(File areaFile) throws IOException;

    Page<Area> findByPage(Pageable pageable);

    List<Area> findAll();

    List<Area> findByQ(String q);

}
