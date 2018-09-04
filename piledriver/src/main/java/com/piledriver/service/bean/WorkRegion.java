package com.piledriver.service.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_work_region")
public class WorkRegion {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, length = 11)
	@Id
	private int id;

	@Column(name = "projectid", nullable = false, length = 11)
	private int projectid;

	@Column(name = "name", nullable = false, length = 64)
	private String name;

	@Column(name = "desc", nullable = true, length = 128)
	private String desc;

	public WorkRegion() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}

	@Override
	public String toString() {
		return "WorkRegion [id=" + id + ", projectid=" + projectid + ", name=" + name + ", desc=" + desc + "]";
	}

	
}