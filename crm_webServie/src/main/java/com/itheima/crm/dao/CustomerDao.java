package com.itheima.crm.dao;

import com.itheima.crm.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer, Integer> {


    List<Customer> findByFixedAreaIdIsNull();

    List<Customer> findByFixedAreaId(String fixedAreaId);

    //清空关联的客户
    @Query(value = "update t_customer set c_fixed_area_id=null  where c_fixed_area_id=?", nativeQuery = true)
    @Modifying
//增删改需要
    void updataByFixedAreaId(String fixedAreaId);

    @Query(value = "update  t_customer set c_fixed_area_id=?2 where c_id=?1", nativeQuery = true)
    @Modifying
    void setFixedId(Integer customerId, String fixedAreaId);

    @Query(value = "update t_customer set C_Fixed_AREA_ID = ? where c_id = ?", nativeQuery = true)
    @Modifying
    void updateByCustomerId(String fixedAreaId, Integer customerId);


    @Query(value = "select * from t_customer where c_fixed_area_Id=?", nativeQuery = true)
    List<Customer> findCusByFixedId(String id);

    List<Customer> findByTelephone(String telephone);

    @Query(value = "update t_customer set c_type=1 where c_telephone=?",nativeQuery = true)
    @Modifying
    void activeCode(String telephone);


    @Query(value = "select * from t_customer where c_telephone=? and c_password=? and c_type=1",nativeQuery = true)
    Customer login(String telephone, String password);

    @Query(value = "select c_fixed_area_id from t_customer where c_address = ?",nativeQuery = true)
    String findFixedAreaIdByAddress(String address);

}
