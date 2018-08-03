package com.itheima.bos.dao;

import com.itheima.domain.Menu;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionDao extends JpaRepository<Permission,Integer> {

    @Query(value ="select * from t_permission p,t_role_permission rp where p.c_id=rp.c_permission_id and c_role_id=? " ,nativeQuery = true)
    List<Permission> findByRole(int id);

    @Query(value ="select * \n" +
            "from T_PERMISSION t,t_role_permission rp,t_user_role ur \n" +
            "where t.c_id=rp.c_permission_id \n" +
            "and rp.c_role_id=ur.c_role_id and ur.c_user_id=?" ,nativeQuery = true)
    List<Permission> findByUser(int id);
}
