package com.piledriver.service.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_project")
public class ProjectInfo {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, length = 11)
	private int id;
	@Id
	@Column(name = "name", nullable = false, length = 64)
	private String name;

	@Column(name = "detail", nullable = true, length = 128)
	private String desc;

	public ProjectInfo() {
	}

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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "ProjectInfo [id=" + id + ", name=" + name + ", desc=" + desc + "]";
	}

}