package com.piledriver.service.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_image")
public class Image implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "imageid", nullable = false, length = 13)
	private String imageid;

	@Lob 
	@Basic(fetch = FetchType.LAZY) 
	@Column(name="image", columnDefinition="BLOB", nullable=false) 
	private byte[] image;

	
	public String getImageid() {
		return imageid;
	}

	public void setImageid(String imageid) {
		this.imageid = imageid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}