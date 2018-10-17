package com.piledriver.service.bean;

public class ConstructionStatistic {
	private int id;
	private double pieces;
	private String type;

	public ConstructionStatistic() {
 
	}

	public ConstructionStatistic(int id, double pieces, String type) {
		super();
		this.id = id;
		this.pieces = pieces;
		this.type = type;
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

	@Override
	public String toString() {
		return "ConstructionStatistic [id=" + id + ", pieces=" + pieces + ", type=" + type + "]";
	}

}
