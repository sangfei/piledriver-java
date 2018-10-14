package com.piledriver.service.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.piledriver.service.bean.WorkRegion;

@Transactional
public interface WorkRegionDao extends CrudRepository<WorkRegion, Integer> {

	public List<WorkRegion> findByProjectid(int projectid);
	
	@Query(value = "select * from tbl_work_region where name=?1 and projectid=?2", nativeQuery = true)
	public WorkRegion findworkregion(String name, int projectid);
	
	@Query(value = "insert into tbl_work_region(name,detail,projectid) value(?1,?2,?3)", nativeQuery = true)
	@Modifying
	public int insertworkregion(String name, String desc, int projectid);
}