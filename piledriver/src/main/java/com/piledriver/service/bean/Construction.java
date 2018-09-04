package com.piledriver.service.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

//@Entity
//@Table(name = "tbl_construction")
public class Construction {
	/**
	 * CREATE TABLE `tbl_construction` ( `id` int(11) unsigned NOT NULL
 `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `date` bigint(11) NOT NULL, 
  `rownumber` int(11) NOT NULL,
  `pilenumber` int(11) NOT NULL,
  `pilelength` double(22,2) NOT NULL,
  `pilevirtuallength` double(22,2) NOT NULL,
  `workregion` int(11) NOT NULL,
  `workleader` int(11) NOT NULL,
	 */

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, length = 11)
	@Id
	private int id;
	@Column(name = "date", nullable = false, length = 20)
	private String date;

	@Column(name = "rownumber", nullable = false, length = 11)
	private int rownumber;

	@Column(name = "pilenumber", nullable = false, length = 11)
	private int pilenumber;
	
	@Column(name = "pilelength", nullable = false, length = 22)
	private double pilelength;	
	
	@Column(name = "pilevirtuallength", nullable = false, length = 22)
	private double pilevirtuallength;
	
	@Column(name = "workregion", nullable = false, length = 11)
	private int workregion;

	@Column(name = "workleader", nullable = false, length = 11)
	private int workleader;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getRownumber() {
		return rownumber;
	}

	public void setRownumber(int rownumber) {
		this.rownumber = rownumber;
	}

	public int getPilenumber() {
		return pilenumber;
	}

	public void setPilenumber(int pilenumber) {
		this.pilenumber = pilenumber;
	}

	public double getPilelength() {
		return pilelength;
	}

	public void setPilelength(double pilelength) {
		this.pilelength = pilelength;
	}

	public double getPilevirtuallength() {
		return pilevirtuallength;
	}

	public void setPilevirtuallength(double pilevirtuallength) {
		this.pilevirtuallength = pilevirtuallength;
	}

	public int getWorkregion() {
		return workregion;
	}

	public void setWorkregion(int workregion) {
		this.workregion = workregion;
	}

	public int getWorkleader() {
		return workleader;
	}

	public void setWorkleader(int workleader) {
		this.workleader = workleader;
	}

	@Override
	public String toString() {
		return "Construction [id=" + id + ", date=" + date + ", rownumber=" + rownumber + ", pilenumber=" + pilenumber
				+ ", pilelength=" + pilelength + ", pilevirtuallength=" + pilevirtuallength + ", workregion="
				+ workregion + ", workleader=" + workleader + "]";
	}
	
	
}
