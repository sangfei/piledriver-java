

package com.piledriver.service.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tbl_equipment")
public class Equipment {
	/**
	 *   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '设备名',
  `brand` varchar(24) NOT NULL DEFAULT '' COMMENT '品牌',
  `model` varchar(24) NOT NULL DEFAULT '' COMMENT '型号',
  `diameter` int(11) DEFAULT NULL COMMENT '直径',
  `ownerid` int(11) DEFAULT NULL COMMENT '负责人',
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", nullable = false, length = 30)
	private String name;

	@Column(name = "brand", nullable = true, length = 24)
	private String brand;

	@Column(name = "diameter", nullable = false, length = 11)
	private int diameter;

	@Column(name = "ownerid", nullable = false, length = 11)
	private int ownerid;

	@Column(name = "model", nullable = false, length = 24)
	private String model;

	public Equipment()
	{
		
	}
	
	public Equipment(int id, String name, String brand, int diameter, int ownerid, String model) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.diameter = diameter;
		this.ownerid = ownerid;
		this.model = model;
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	public int getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(int ownerid) {
		this.ownerid = ownerid;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", brand=" + brand + ", diameter=" + diameter + ", ownerid=" + ownerid
				+ ", model=" + model + "]";
	}

}