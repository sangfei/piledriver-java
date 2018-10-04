package com.piledriver.service.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.piledriver.service.bean.WorkRegion;

@Transactional
public interface WorkRegionDao extends CrudRepository<WorkRegion, Integer> {

	public List<WorkRegion> findByProjectid(int projectid);

}