package com.piledriver.service.bean;

public class ConstructionStatistic {
	private int id;
	private int projectid;
	private int workregionid;
	private int equipmentid;
	private int ownerid;
	private String projectname;
	private String workregionname;
	private String equipmentname;
	private String ownername;
	private double totalpieces;
	private double pieces;
	private String type;

	public ConstructionStatistic() {

	}

	public ConstructionStatistic(int id, int projectid, int workregionid, int equipmentid, int ownerid,
			String projectname, String workregionname, String equipmentname, String ownername, double totalpieces,
			double pieces, String type) {
		super();
		this.id = id;
		this.projectid = projectid;
		this.workregionid = workregionid;
		this.equipmentid = equipmentid;
		this.ownerid = ownerid;
		this.projectname = projectname;
		this.workregionname = workregionname;
		this.equipmentname = equipmentname;
		this.ownername = ownername;

		this.totalpieces = totalpieces;
		this.pieces = pieces;
		this.type = type;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getWorkregionname() {
		return workregionname;
	}

	public void setWorkregionname(String workregionname) {
		this.workregionname = workregionname;
	}

	public String getEquipmentname() {
		return equipmentname;
	}

	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}

	public String getOwnername() {
		return ownername;
	}

	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}

	public double getTotalpieces() {
		return totalpieces;
	}

	public void setTotalpieces(double totalpieces) {
		this.totalpieces = totalpieces;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPieces() {
		return pieces;
	}

	public void setPieces(double pieces) {
		this.pieces = pieces;
	}

	public String getType() {
		return type;
	}

	public void setcType(String type) {
		this.type = type;
	}

	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}

	public int getWorkregionid() {
		return workregionid;
	}

	public void setWorkregionid(int workregionid) {
		this.workregionid = workregionid;
	}

	public int getEquipmentid() {
		return equipmentid;
	}

	public void setEquipmentid(int equipmentid) {
		this.equipmentid = equipmentid;
	}

	public int getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(int ownerid) {
		this.ownerid = ownerid;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ConstructionStatistic [id=" + id + ", projectid=" + projectid + ", workregionid=" + workregionid
				+ ", equipmentid=" + equipmentid + ", ownerid=" + ownerid + ", projectname=" + projectname
				+ ", workregionname=" + workregionname + ", equipmentname=" + equipmentname + ", ownername=" + ownername
				+ ", totalpieces=" + totalpieces + ", pieces=" + pieces + ", type=" + type + "]";
	}

}
