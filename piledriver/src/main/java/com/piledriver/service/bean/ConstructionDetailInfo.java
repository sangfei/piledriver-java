package com.piledriver.service.bean;

public class ConstructionDetailInfo {

	private Integer date;

	private Integer rownumber;

	private Integer pilenumber;

	private Double pilelength;

	private Double pilevirtuallength;

	private String stuff;

	public ConstructionDetailInfo() {
	}

	public ConstructionDetailInfo(Integer date, Integer rownumber, Integer pilenumber, Double pilelength,
			Double pilevirtuallength, String stuff) {
		this.date = date;
		this.rownumber = rownumber;
		this.pilenumber = pilenumber;
		this.pilelength = pilelength;
		this.pilevirtuallength = pilevirtuallength;
		this.stuff = stuff;
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public Integer getRownumber() {
		return rownumber;
	}

	public void setRownumber(Integer rownumber) {
		this.rownumber = rownumber;
	}

	public Integer getPilenumber() {
		return pilenumber;
	}

	public void setPilenumber(Integer pilenumber) {
		this.pilenumber = pilenumber;
	}

	public Double getPilelength() {
		return pilelength;
	}

	public void setPilelength(Double pilelength) {
		this.pilelength = pilelength;
	}

	public Double getPilevirtuallength() {
		return pilevirtuallength;
	}

	public void setPilevirtuallength(Double pilevirtuallength) {
		this.pilevirtuallength = pilevirtuallength;
	}

	public String getStuff() {
		return stuff;
	}

	public void setStuff(String stuff) {
		this.stuff = stuff;
	}

	@Override
	public String toString() {
		return "ConstructionDetailInfo [date=" + date + ", rownumber=" + rownumber + ", pilenumber=" + pilenumber
				+ ", pilelength=" + pilelength + ", pilevirtuallength=" + pilevirtuallength + ", stuff=" + stuff + "]";
	}

}
