package com.itheima.bos.dao;

import com.itheima.domain.SubArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubAreaDao extends JpaRepository<SubArea,String> {

    @Query(value ="select  * from t_sub_area where c_fixedarea_id=?" ,nativeQuery = true)
    List<SubArea> findByDecideId(String decidedzoneid);

    List<SubArea> findByAreaId(String areaId);
    @Query(value ="select a.c_province,count(*) \n" +
            "from t_area a,t_sub_area sa \n" +
            "where a.c_id=sa.c_area_id \n" +
            "group by a.c_province" ,nativeQuery = true)
    List<Object> findSubAreas();
}
