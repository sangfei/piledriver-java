package com.piledriver.service.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.piledriver.service.bean.Construction;

@Transactional
public interface ConstructionDao extends CrudRepository<Construction, Integer> {

	// // 利用原生的SQL进行插入操作
	// @Query(value = "select t1.date, t1.rownumber, t1.pilenumber, t1.pilelength,
	// t1.pilevirtuallength, t2.name as stuff "
	// + "from tbl_construction t1 left join tbl_employee t2 on t1.workleader =
	// t2.id left join tbl_title t3 on t2.title=t3.id "
	// + "where t1.workregion = :workregionId and t3.name = '施工班组组长'", nativeQuery =
	// true)
	// List<Object[]> queryDetails(@Param("workregionId") int workregionId);

	// 利用原生的SQL进行插入操作
	@Query(value = "select * from tbl_construction where date >= :starttime and date <= :endtime", nativeQuery = true)
	List<Object[]> queryByTimeRange(@Param("starttime") int starttime, @Param("endtime") int endtime);

	@Query(value = "select * from tbl_construction where date >= ?1 and date <= ?2 and workregion = ?3", nativeQuery = true)
	List<Object[]> queryByWorkRegionTimeRange(int starttime, int endtime, int workregion);

	@Query(value = "select * from tbl_construction where date >= ?1 and date <= ?2 and ownerid = ?3", nativeQuery = true)
	List<Object[]> queryByOwnerTimeRange(int starttime, int endtime, int ownerid);
	
	@Query(value = "select ownerid as id, sum(pieces) as pieces,'owner' as type from tbl_construction where date >= ?1 and date <= ?2 group by ownerid", nativeQuery = true)
	List<Object[]> groupByOwner(int starttime, int endtime, int ownerid);

	@Query(value = "select equipmentid as id, sum(pieces) as pieces, 'equipment' as type from tbl_construction where date >= ?1 and date <= ?2 and workregion = ?3 group by equipmentid", nativeQuery = true)
	List<Object[]> groupByEquipMentOfWorkRegion(int starttime, int endtime, int workregion);

}