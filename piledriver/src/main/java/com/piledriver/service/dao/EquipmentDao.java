package com.piledriver.service.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.piledriver.service.bean.Equipment;

@Transactional
public interface EquipmentDao extends CrudRepository<Equipment, Integer> {

	public Equipment findById(int id);

	public List<Equipment> findByOwnerid(int ownerid);

	// 利用原生的SQL进行插入操作
	@Query(value = "insert into tbl_equipment(name, brand, title, ownerid, model) value(?1,?2,?3,?4,?5)", nativeQuery = true)
	@Modifying
	public int insertEquipment(String name, String brand, int title, int ownerid, String model);

	public Equipment findByName(String name);
}