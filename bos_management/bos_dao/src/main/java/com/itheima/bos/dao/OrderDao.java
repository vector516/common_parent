package com.itheima.bos.dao;

import com.itheima.domain.Area;
import com.itheima.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDao extends JpaRepository<Order,Integer> {

//    @Query(value ="select * from t_area where c_province =? and c_city=? and c_district=?",nativeQuery = true)
//    List<Area> findByPCD(String provice, String city, String distinct);
}
