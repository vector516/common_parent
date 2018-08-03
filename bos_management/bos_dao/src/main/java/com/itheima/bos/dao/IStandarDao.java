package com.itheima.bos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itheima.domain.Standard;

public interface IStandarDao extends JpaRepository<Standard, Integer>{

	Standard findByName(String string);

	Standard findBynameLike(String string);
//
	Standard findByNameAndOperator(String string, String string2);
//
//	Standard findBynameAndoperator(String string, String string2);
//
//	Standard findBynameAndOperator(String string, String string2);
	
	@Query("from Standard where name = ?")
	Standard findByNamex(String string);

	@Query("from Standard where name like ?")
	Standard findBynameLikex(String string);
	
	@Query("from Standard where name =?2 and operator= ?1")
	Standard findByNameAndOperatorx(String string, String string2);

}
