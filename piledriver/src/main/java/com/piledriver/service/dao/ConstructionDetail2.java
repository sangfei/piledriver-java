package com.piledriver.service.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.piledriver.service.bean.WorkRegion;

@Transactional
public interface ConstructionDetail2 extends CrudRepository<WorkRegion, Integer> {

	// 利用原生的SQL进行插入操作
	@Query(value = "select t1.date, t1.rownumber, t1.pilenumber, t1.pilelength, t1.pilevirtuallength, t2.name as stuff "
			+ "from tbl_construction t1 left join tbl_employee t2 on t1.workleader = t2.id left join tbl_title t3 on t2.title=t3.id "
			+ "where t1.workregion = :workregionId and t3.name = '施工班组组长'", nativeQuery = true)
	List<Object[]> queryDetails(@Param("workregionId") int workregionId);

}