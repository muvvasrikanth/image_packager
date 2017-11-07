package com.qs.image_packager.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ImageDownloadJob {

	public static final String IN_PROCESS = "IP";
	public static final String EMAIL_SENT = "ES" ;
	public static final String ERROR = "ER" ;
	public static final String FAILED = "FL" ;
	public static final String COMPLETE = "CO" ;
	public static final String CLEAN_TEMP_FILES = "TF" ;
	public static final String ZIP_FILES_CREATED = "ZC";
	public static final String NEW = "N";

	private Integer id ;
	
	private Date downloadDate ;
	
	private String email ;
	
	private String customerNumber ;
	
	private String styles ;
	
	private String s3Path ;
	
	private String status ;
	
	private String statusMessage ;
	
	private Date lastActiveDate ;
	
	private String jobCode ;

	private ArrayList<String> zipFileNames;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDownloadDate() {
		return downloadDate;
	}

	public void setDownloadDate(Date downloadDate) {
		this.downloadDate = downloadDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStyles() {
		return styles;
	}

	public void setStyles(String styles) {
		this.styles = styles;
	}

	public String getS3Path() {
		return s3Path;
	}

	public void setS3Path(String s3Path) {
		this.s3Path = s3Path;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public Date getLastActiveDate() {
		return lastActiveDate;
	}

	public void setLastActiveDate(Date lastActiveDate) {
		this.lastActiveDate = lastActiveDate;
	}

	public String getJobCode() {
		return jobCode;
	}
	
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	
	public List<String> getStylesAsList() {
		String[] sArray = styles.split(",") ;
		List <String> sList = new ArrayList<String> () ;
		for(String s : sArray){
			sList.add(s) ;
		}
		return sList ;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public List<String> getZipFileNames() {
		if(zipFileNames == null){
			zipFileNames = new ArrayList <String> () ;
		}
		return zipFileNames ;
	}

	
}
