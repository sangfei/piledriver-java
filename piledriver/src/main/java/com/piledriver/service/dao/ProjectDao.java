package com.piledriver.service.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.piledriver.service.bean.ProjectInfo;

@Transactional
public interface ProjectDao extends CrudRepository<ProjectInfo, Integer> {

	// 利用原生的SQL进行插入操作
	@Query(value = "insert into tbl_project(name,detail) value(?1,?2)", nativeQuery = true)
	@Modifying
	public int insertProject(String name, String desc);

	@Query(value = "update tbl_project set detail=?1 where name=?2", nativeQuery = true)
	@Modifying
	public int upateProject(String desc, String name);
	
	public ProjectInfo findByName(String name);
}