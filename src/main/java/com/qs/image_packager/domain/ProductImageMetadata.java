package com.qs.image_packager.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductImageMetadata {

	private String id ;
	
	private String styleNumber ;
	
	private String colorCode ;
	
	private Integer size ;
	
	private String angle ;
	
	private Boolean CAD ;
	
	private Boolean lineBook ;
	
	private String fileName ;
	
	private String s3Path ;
	
	private Date uploadDate ;
	
	private String jobCode ;
	
	private Date createdDateTime ;
	
	private Date modifiedDateTime ;
	
	private String imageType ;
	
	private String oldJobCode ;
	
	private String materialNumber ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStyleNumber() {
		return styleNumber;
	}

	public void setStyleNumber(String styleNumber) {
		this.styleNumber = styleNumber;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getAngle() {
		return angle;
	}

	public void setAngle(String angle) {
		this.angle = angle;
	}

	public Boolean getCAD() {
		return CAD;
	}

	public void setCAD(Boolean cAD) {
		CAD = cAD;
	}

	public Boolean getLineBook() {
		return lineBook;
	}

	public void setLineBook(Boolean lineBook) {
		this.lineBook = lineBook;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getS3Path() {
		return s3Path;
	}

	public void setS3Path(String s3Path) {
		this.s3Path = s3Path;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Date getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(Date modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getOldJobCode() {
		return oldJobCode;
	}

	public void setOldJobCode(String oldJobCode) {
		this.oldJobCode = oldJobCode;
	}

	public String getMaterialNumber() {
		return materialNumber;
	}

	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}
	
	
}
