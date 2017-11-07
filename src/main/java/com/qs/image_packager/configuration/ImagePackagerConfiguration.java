package com.qs.image_packager.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImagePackagerConfiguration {

	@Value("${base.s3.url}")
	private String baseUrl;
	
	@Value("${s3.service.url}")
	private String s3ServiceUrl ;
	
	@Value("${s3.bucket.url}")
	private String s3BucketUrl ;

	@Value("${s3.bucket.name}")
	private String s3BucketName ;

	@Value("${fs.temp.path}")
	private String fsTempPath;
	
	@Value("${s3.access.key}")
	private String s3AccessKey ;
	
	@Value("${s3.secret.key}")
	private String s3SecretKey ;

	@Value("${max.zip.file.size.mbytes}")
	private Integer maxZipFileSize;
	
	@Value("${support.email.address}")
	private String supportEmailAddress;
	
	public String getS3SecretKey() {
		return s3SecretKey;
	}

	public void setS3SecretKey(String s3SecretKey) {
		this.s3SecretKey = s3SecretKey;
	}

	public String getS3BucketName() {
		return s3BucketName;
	}

	public void setS3BucketName(String s3BucketName) {
		this.s3BucketName = s3BucketName;
	}

	public String getS3ServiceUrl() {
		return s3ServiceUrl;
	}

	public void setS3ServiceUrl(String s3ServiceUrl) {
		this.s3ServiceUrl = s3ServiceUrl;
	}

	public String getS3BucketUrl() {
		return s3BucketUrl;
	}

	public void setS3BucketUrl(String s3BucketUrl) {
		this.s3BucketUrl = s3BucketUrl;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getFsTempPath() {
		return fsTempPath ;
	} 

	public void setFsTempPath(String fsTempPath) {
		this.fsTempPath = fsTempPath ;
	}

	public int getMaxZipFileSize() {
		return this.maxZipFileSize;
	}

	public void setMaxZipFileSize(Integer maxZipFileSize) {
		this.maxZipFileSize = maxZipFileSize;
	}

	public String getS3AccessKey() {
		return this.s3AccessKey ;
	}
	
	public void setS3AccessKey(String s3AccessKey){
		this.s3AccessKey = s3AccessKey ;
	}

	public String getSupportEmailAddress() {
		return this.supportEmailAddress ;
	}
	
	public void setSupportEmailAddress(String supportEmailAddress){
		this.supportEmailAddress = supportEmailAddress ;
	}
}


