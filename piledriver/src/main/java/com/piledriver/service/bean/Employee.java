package com.piledriver.service.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL DEFAULT '',
  `sex` varchar(5) NOT NULL DEFAULT '',
  `birth` varchar(11) DEFAULT NULL,
  `title` int(11) DEFAULT NULL,
  `pwd` varchar(32) NOT NULL DEFAULT '',
  `phone` varchar(11) NOT NULL DEFAULT '',

 * @author sangfei
 *
 */
@Entity
@Table(name = "tbl_employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", nullable = false, length = 30)
	private String name;
	
	@Column(name = "sex", nullable = false, length = 5)
	private String sex;
	
	@Column(name = "title", nullable = true, length = 11)
	private int title;
	
	@Column(name = "pwd", nullable = false, length = 32)
	private String pwd;
	
	@Column(name = "phone", nullable = false, length = 11)
	private String phone;

	@Temporal(TemporalType.DATE)
	@Column(name = "birth", nullable = true, length = 12)
	private Date birth;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getTitle() {
		return title;
	}

	public void setTitle(int title) {
		this.title = title;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", sex=" + sex + ", title=" + title + ", pwd=" + pwd
				+ ", phone=" + phone + ", birth=" + birth + "]";
	}
	
	
}