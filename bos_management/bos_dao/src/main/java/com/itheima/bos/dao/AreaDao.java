package com.itheima.bos.dao;

import com.itheima.domain.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AreaDao extends JpaRepository<Area,String> {
    //没有涉及到表中数据的增删改,所有不需要要@Modify提交事务
    @Query(value ="select * from t_area where c_city like ?1 or upper(c_citycode) like ?1 or c_district like ?1 or c_province like ?1 or c_shortcode like ?1" ,nativeQuery = true)
    List<Area> findByQ(String q);


    @Query(value ="select * from t_area where c_province =? and c_city=? and c_district=?",nativeQuery = true)
     Area findByPCD(String provice, String city, String distinct);


    @Query(value ="select * from t_area where c_province =? and c_city=? and c_district=?",nativeQuery = true)
    Area findByProvinceAndCityAndDistrict(String province, String city, String district);
}
