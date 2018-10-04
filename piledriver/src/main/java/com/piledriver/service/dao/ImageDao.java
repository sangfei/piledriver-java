package com.piledriver.service.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.piledriver.service.bean.Image;

@Transactional
public interface ImageDao extends CrudRepository<Image, Integer> {

	public Image findByImageid(String imageid);

	// 利用原生的SQL进行插入操作
	@Query(value = "insert into tbl_image(image, imageid) value(?1,?2)", nativeQuery = true)
	@Modifying
	public int saveImage(byte[] iamge,String imageid);
}