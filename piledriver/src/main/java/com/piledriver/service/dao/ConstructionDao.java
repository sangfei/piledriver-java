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
	@Query(value = "select * from tbl_construction where date >= :starttime and date < :endtime", nativeQuery = true)
	List<Object[]> queryByTimeRange(@Param("starttime") int starttime, @Param("endtime") int endtime);

	@Query(value = "select * from tbl_construction where date >= ?1 and date < ?2 and workregion = ?3", nativeQuery = true)
	List<Object[]> queryByWorkRegionTimeRange(int starttime, int endtime, int workregion);

	@Query(value = "select * from tbl_construction where date >= ?1 and date < ?2 and ownerid = ?3", nativeQuery = true)
	List<Object[]> queryByOwnerTimeRange(int starttime, int endtime, int ownerid);
	@Query(value = "select * from tbl_construction where date >= ?1 and date < ?2 and equipmentid = ?3", nativeQuery = true)
	List<Object[]> queryByEquipmentTimeRange(int starttime, int endtime, int equipmentid);

	@Query(value = "select a.id,  a.projectid,  a.workregion,  a.equipmentid,  a.ownerid,a.projectname, a.workregionname,a.equipmentname,a.ownername, COALESCE(a.pieces, 0 ) as totalpieces,  COALESCE(t.pieces,0) as pieces, ?3 as type from (select id,  projectid,  workregion,  equipmentid,  ownerid, sum(pieces) as pieces,?3  as type ,projectname, workregionname,equipmentname,ownername from v_names where date >= 0 and date < ?2 group by ownerid) a  left join (select id,  projectid,  workregion,  equipmentid,  ownerid, sum(pieces) as pieces,?3 as type from tbl_construction where date >= ?1 and date < ?2 group by ownerid) t on t.ownerid = a.ownerid where 1=1", nativeQuery = true)
	List<Object[]> groupByOwner(int starttime, int endtime, String qtype);

	@Query(value = "select a.id,  a.projectid,  a.workregion,  a.equipmentid,  a.ownerid,a.projectname, a.workregionname,a.equipmentname,a.ownername, COALESCE(a.pieces, 0 ) as totalpieces,  COALESCE(t.pieces,0) as pieces, ?3 as type from (select id,  projectid,  workregion,  equipmentid,  ownerid, sum(pieces) as pieces,?3  as type ,projectname, workregionname,equipmentname,ownername from v_names where date >= 0 and date < ?2 group by projectid) a  left join (select id,  projectid,  workregion,  equipmentid,  ownerid, sum(pieces) as pieces,?3 as type from tbl_construction where date >= ?1 and date < ?2 group by projectid) t on t.projectid = a.projectid where 1=1", nativeQuery = true)
	List<Object[]> groupByProject(int starttime, int endtime, String qtype);

	@Query(value = "select a.id,  a.projectid,  a.workregion,  a.equipmentid,  a.ownerid,a.projectname, a.workregionname,a.equipmentname,a.ownername, COALESCE(a.pieces, 0 ) as totalpieces,  COALESCE(t.pieces,0) as pieces, ?3 as type from (select id,  projectid,  workregion,  equipmentid,  ownerid, sum(pieces) as pieces,?3  as type ,projectname, workregionname,equipmentname,ownername from v_names where date >= 0 and date < ?2 group by workregion) a  left join (select id,  projectid,  workregion,  equipmentid,  ownerid, sum(pieces) as pieces,?3 as type from tbl_construction where date >= ?1 and date < ?2 group by workregion) t on t.workregion = a.workregion where 1=1", nativeQuery = true)
	List<Object[]> groupByWorkregion(int starttime, int endtime, String qtype);
	

	@Query(value = "select a.id,  a.projectid,  a.workregion,  a.equipmentid,  a.ownerid,a.projectname, a.workregionname,a.equipmentname,a.ownername, COALESCE(a.pieces, 0 ) as totalpieces,  COALESCE(t.pieces,0) as pieces, ?3 as type from (select id,  projectid,  workregion,  equipmentid,  ownerid, sum(pieces) as pieces,?3  as type ,projectname, workregionname,equipmentname,ownername from v_names where date >= 0 and date < ?2 group by equipmentid) a  left join (select id,  projectid,  workregion,  equipmentid,  ownerid, sum(pieces) as pieces,?3 as type from tbl_construction where date >= ?1 and date < ?2 group by equipmentid) t on t.equipmentid = a.equipmentid where 1=1", nativeQuery = true)
	List<Object[]> groupByEquipment(int starttime, int endtime, String qtype);

	@Query(value = "select a.id,  a.projectid,  a.workregion,  a.equipmentid,  a.ownerid,a.projectname, a.workregionname,a.equipmentname,a.ownername, COALESCE(a.pieces, 0 ) as totalpieces,  COALESCE(t.pieces,0) as pieces, ?4 as type from (select id,  projectid,  workregion,  equipmentid,  ownerid, sum(pieces) as pieces,?4  as type ,projectname, workregionname,equipmentname,ownername from v_names where date >= 0 and date < ?2 and workregion = ?3 group by equipmentid) a  left join (select id,  projectid,  workregion,  equipmentid,  ownerid, sum(pieces) as pieces,?4 as type from tbl_construction where date >= ?1 and date < ?2 and workregion = ?3 group by equipmentid) t on t.equipmentid = a.equipmentid where 1=1", nativeQuery = true)
	List<Object[]> groupByEquipMentOfWorkRegion(int starttime, int endtime, int workregion, String qtype);

	@Query(value = "select a.id,  a.projectid,  a.workregion,  a.equipmentid,  a.ownerid,a.projectname, a.workregionname,a.equipmentname,a.ownername, COALESCE(a.pieces, 0 ) as totalpieces,  COALESCE(t.pieces,0) as pieces, ?4 as type from (select id,  projectid,  workregion,  equipmentid,  ownerid, sum(pieces) as pieces,?4  as type ,projectname, workregionname,equipmentname,ownername from v_names where date >= 0 and date < ?2 and equipmentid = ?3 group by equipmentid) a  left join (select id,  projectid,  workregion,  equipmentid,  ownerid, sum(pieces) as pieces,?4 as type from tbl_construction where date >= ?1 and date < ?2 and equipmentid = ?3 group by equipmentid) t on t.equipmentid = a.equipmentid where 1=1", nativeQuery = true)
	List<Object[]> groupByEquipMentOfEquipment(int starttime, int endtime, int equipmentid, String qtype);

	@Query(value = "select a.id,  a.projectid,  a.workregion,  a.equipmentid,  a.ownerid,a.projectname, a.workregionname,a.equipmentname,a.ownername, COALESCE(a.pieces, 0 ) as totalpieces,  COALESCE(t.pieces,0) as pieces, ?4 as type from (select id,  projectid,  workregion,  equipmentid,  ownerid, sum(pieces) as pieces,?4  as type ,projectname, workregionname,equipmentname,ownername from v_names where date >= 0 and date < ?2 and workregion = ?3 group by workregion) a  left join (select id,  projectid,  workregion,  equipmentid,  ownerid, sum(pieces) as pieces,?4 as type from tbl_construction where date >= ?1 and date < ?2 and workregion = ?3 group by workregion) t on t.workregion = a.workregion where 1=1", nativeQuery = true)
	List<Object[]> groupByWorkRegionOfWorkRegion(int starttime, int endtime, int workregion, String qtype);
	
	@Query(value = "select a.id,  a.projectid,  a.workregion,  a.equipmentid,  a.ownerid,a.projectname, a.workregionname,a.equipmentname,a.ownername, COALESCE(pieces, 0 ) as totalpieces,  COALESCE(pieces,0) as pieces, :type as type  from v_names a where date >= :starttime and date < :endtime and workregion = :workregion", nativeQuery = true)
	List<Object[]> queryStatByTimeRange(@Param("starttime") int starttime, @Param("endtime") int endtime, @Param("workregion") int workregion, @Param("type") String qtype);
	
	@Query(value = "select a.id,  a.projectid,  a.workregion,  a.equipmentid,  a.ownerid,a.projectname, a.workregionname,a.equipmentname,a.ownername, COALESCE(pieces, 0 ) as totalpieces,  COALESCE(pieces,0) as pieces, :type as type  from v_names a where date >= :starttime and date < :endtime and equipmentid = :equipmentid", nativeQuery = true)
	List<Object[]> queryStatByTimeRangeEquipment(@Param("starttime") int starttime, @Param("endtime") int endtime, @Param("equipmentid") int equipmentid, @Param("type") String qtype);

}