package com.itheima.bos.dao;

import com.itheima.domain.Courier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourierDao extends JpaRepository<Courier,Integer>,JpaSpecificationExecutor  {

    @Query(value = "update t_courier set c_deltag = 1 where c_id=?",nativeQuery = true)
    @Modifying
    void update(Integer id);

    List<Courier> findByDeltagIsNull();

}
