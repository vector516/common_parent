package com.itheima.bos.dao;

import com.itheima.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleDao extends JpaRepository<Role,Integer> {
    @Query(value ="select * from t_role r,t_user_role u where r.c_id=u.c_role_id and u.c_user_id =?" ,nativeQuery = true)
    List<Role> findByUser(int id);

}
