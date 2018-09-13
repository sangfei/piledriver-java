package com.piledriver.service.dao;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.piledriver.service.bean.Employee;

@Transactional
public interface EmployeeDao extends CrudRepository<Employee, Integer> {
	
	public Employee findByPhone(String phone);

	// 利用原生的SQL进行插入操作
	@Query(value = "insert into tbl_employee(name, sex, pwd, phone, birth, title) value(?1,?2,?3,?4,?5,?6)", nativeQuery = true)
	@Modifying
	public int insertStuff(String name, String sex, String pwd, String phone, Date birth, Integer title);
}