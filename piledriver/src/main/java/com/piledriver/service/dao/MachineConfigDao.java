package com.piledriver.service.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.piledriver.service.bean.MachineConfig;

@Transactional
public interface MachineConfigDao extends CrudRepository<MachineConfig, Integer> {
	public MachineConfig findByMachineName(String machinename);

	// 利用原生的SQL进行插入操作
	@Query(value = "insert into tbl_config(machinename,delayseconds,durationseconds) value(?1,?2,?3)", nativeQuery = true)
	@Modifying
	public int insertConfig(String name, int delay, int duration);

	@Query(value = "update tbl_config set delayseconds=?1 , durationseconds=?2 where machinename=?3", nativeQuery = true)
	@Modifying
	public int upateConfig(Integer delay, Integer duration, String name);
}