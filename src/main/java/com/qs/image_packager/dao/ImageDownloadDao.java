package com.qs.image_packager.dao;

import java.sql.SQLException;
import java.util.List;

import com.qs.image_packager.domain.ImageDownloadJob;
import com.qs.image_packager.domain.ProductImageMetadata;

public interface ImageDownloadDao {

	public List<ProductImageMetadata> getProductImageMetadata(List<String> styles) throws SQLException ;
	
	public void updateImageDownloadJob(ImageDownloadJob job) ;
	
}
