package com.piledriver.service.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_construction")
public class Construction {
	/*
	 * `id` int(11) unsigned NOT NULL AUTO_INCREMENT, `date` int(11) NOT NULL
	 * COMMENT '施工日期', `pieces` double(22,2) NOT NULL COMMENT '施工完成根数', `workregion`
	 * int(11) NOT NULL COMMENT '施工地块', `reporterid` int(11) DEFAULT NULL COMMENT
	 * '填报人', `status` int(11) DEFAULT NULL COMMENT '生产状态停工或施工中', `reason`
	 * varchar(128) DEFAULT NULL COMMENT '原因', `equipmentid` int(11) DEFAULT NULL
	 * COMMENT '设备编号', `imageid` varchar(13) DEFAULT NULL COMMENT '原因图像', `weather`
	 * varchar(12) DEFAULT NULL COMMENT '天气', `ownerid` int(11) DEFAULT NULL COMMENT
	 * '施工负责人',
	 */
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int date;
	private double pieces;
	private int workregion;
	private int reporterid;
	private int status;
	private String reason;
	private int equipmentid;
	private String imageid;
	private String weather;
	private int ownerid;
	private int projectid;

	public Construction() {
	}

	public Construction(int id, int date, double pieces, int workregion, int reporterid, int status, String reason,
			int equipmentid, String imageid, String weather, int ownerid, int projectid) {
		this.id = id;
		this.date = date;
		this.pieces = pieces;
		this.workregion = workregion;
		this.reporterid = reporterid;
		this.projectid = projectid;
		this.status = status;
		this.reason = reason;
		this.equipmentid = equipmentid;
		this.imageid = imageid;
		this.weather = weather;
		this.ownerid = ownerid;
	}

	public Construction(int date, double pieces, int workregion, int reporterid, int status, String reason,
			int equipmentid, String imageid, String weather, int ownerid, int projectid) {
		this.date = date;
		this.pieces = pieces;
		this.workregion = workregion;
		this.reporterid = reporterid;
		this.projectid = projectid;
		this.status = status;
		this.reason = reason;
		this.equipmentid = equipmentid;
		this.imageid = imageid;
		this.weather = weather;
		this.ownerid = ownerid;
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public double getPieces() {
		return pieces;
	}

	public void setPieces(double pieces) {
		this.pieces = pieces;
	}

	public int getWorkregion() {
		return workregion;
	}

	public void setWorkregion(int workregion) {
		this.workregion = workregion;
	}

	public int getReporterid() {
		return reporterid;
	}

	public void setReporterid(int reporterid) {
		this.reporterid = reporterid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getEquipmentid() {
		return equipmentid;
	}

	public void setEquipmentid(int equipmentid) {
		this.equipmentid = equipmentid;
	}

	public String getImageid() {
		return imageid;
	}

	public void setImageid(String imageid) {
		this.imageid = imageid;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public int getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(int ownerid) {
		this.ownerid = ownerid;
	}

	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}

	@Override
	public String toString() {
		return "Construction [id=" + id + ", date=" + date + ", pieces=" + pieces + ", workregion=" + workregion
				+ ", reporterid=" + reporterid + ", projectid=" + projectid + ", status=" + status + ", reason="
				+ reason + ", equipmentid=" + equipmentid + ", imageid=" + imageid + ", weather=" + weather
				+ ", ownerid=" + ownerid + "]";
	}

}
