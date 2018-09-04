package com.piledriver.service.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.piledriver.service.bean.User;

@Transactional
public interface UserDao extends CrudRepository<User, Integer> {
	public List<User> findByName(String name);

	public List<User> findBySex(char sex);

	public List<User> findByBirthday(Date birthday);

	public List<User> findBySendtime(Date sendtime);

	public List<User> findByPrice(BigDecimal price);

	public List<User> findByFloatprice(float floatprice);

	public List<User> findByDoubleprice(double doubleprice);
}