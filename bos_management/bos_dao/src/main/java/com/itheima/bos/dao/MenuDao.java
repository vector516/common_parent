package com.itheima.bos.dao;

import com.itheima.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuDao extends JpaRepository<Menu,Integer> {

    public List<Menu> findByParentMenuIsNull();

    @Query(value = "select * \n" +
            "from t_menu m,t_role_menu rm,t_user_role ur \n" +
            "where m.c_id=rm.c_menu_id \n" +
            "and rm.c_role_id=ur.c_role_id\n" +
            "and ur.c_user_id=?",nativeQuery = true)
    List<Menu> findByUser(int id);
}
